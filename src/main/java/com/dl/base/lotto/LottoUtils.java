package com.dl.base.lotto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.dl.base.lotto.entity.LottoInfoEntity;
import com.dl.base.lotto.entity.LottoItemEntity;
import com.dl.base.lotto.entity.LottoPrizeLevel;
import com.dl.base.lotto.entity.LottoResultEntity;

/**
 * 大乐透工具类
 * @author
 * @date 2019.01.15
 */
public class LottoUtils {

	
	public static void main(String[] args) {
		String src = "02,10,13,16,19,32|01,03,09";
		String target = "02,10,13,16,19|01,02";
		
		LottoResultEntity resultEntity = calPrizeLevel(src, target);
		System.out.println("resultEntity:" + resultEntity);
		if(resultEntity.lottoLevel != null) {
			System.out.println("level:" + resultEntity.lottoLevel.level);
			System.out.println("中奖级别:" + resultEntity.lottoLevel.toCompoundPrizeLevle());
		}
		BigDecimal r = LottoMoneyUtil.calculate(resultEntity, BigDecimal.valueOf(16859000),BigDecimal.valueOf(600),BigDecimal.valueOf(200),false);
		System.out.println("r:" + r);
	}
	
	private static List<Integer> convertToInt(List<LottoItemEntity> sList){
		List<Integer> rList = new ArrayList<Integer>();
		if(sList != null && sList.size() > 0) {
			for(LottoItemEntity itemEntity : sList) {
				Integer data = itemEntity.num;
				rList.add(data);
			}
		}
		return rList;
	}

	private static List<List<LottoItemEntity>> reConvertToList(List<List<Integer>> sList){
		 List<List<LottoItemEntity>> resultList = new ArrayList<List<LottoItemEntity>>();
		for(List<Integer> tList : sList) {
			if(tList != null && tList.size() > 0) {
				List<LottoItemEntity> rList = new ArrayList<LottoItemEntity>();
				for(Integer item : tList) {
					LottoItemEntity itemEntity = new LottoItemEntity();
					itemEntity.num = item;
					rList.add(itemEntity);
				}
				resultList.add(rList);
			}
		}
		
		return resultList;
	}
	
	/**
	 * 计算开奖状态
	 * @param src 用户号码
	 * @param target  中奖号
	 * @return
	 */
	public static final LottoResultEntity calPrizeLevel(String src,String target) {
		LottoResultEntity lottoResultEntity = new LottoResultEntity();
		if(!isLegal(src)) {
			lottoResultEntity.status = LottoResultEntity.STATUS_NOT_ILLEGAL;
			lottoResultEntity.reason = "用户号码投注号码有误~";
			return lottoResultEntity;
		}
		if(!isLegal(target)) {
			lottoResultEntity.status = LottoResultEntity.STATUS_NOT_ILLEGAL;
			lottoResultEntity.reason = "开奖号码有误~";
			return lottoResultEntity;
		}
		LottoInfoEntity srcEntity = parseInfo(src);
		LottoInfoEntity targetEntity = parseInfo(target);
		//计算单式还是复试
		boolean isCompund = false;
		if(srcEntity.redList.size() > LottoCommon.RED_BALL_SIZE || srcEntity.blueList.size() > LottoCommon.BLUE_BALL_SIZE) {
			isCompund = true;
		}
		lottoResultEntity.isCompund = isCompund;
		if(isCompund) {
			System.out.println("复式算法");
			//红球组合
			List<LottoItemEntity> redList = srcEntity.redList;
			List<Integer> redIntList = convertToInt(redList);
			LottoGroup lottoGrop = new LottoGroup(redIntList,LottoCommon.RED_BALL_SIZE);
			List<List<Integer>> resultRedIntList = lottoGrop.cal();
			List<List<LottoItemEntity>> resultRedList = reConvertToList(resultRedIntList);
			System.out.println("src:" + src + " 红球组合种类:" + resultRedList.size());
			
			//篮球组合
			List<LottoItemEntity> blueList = srcEntity.blueList;
			List<Integer> blueIntList = convertToInt(blueList);
			lottoGrop = new LottoGroup(blueIntList,LottoCommon.BLUE_BALL_SIZE);
			List<List<Integer>> resultBlueIntList = lottoGrop.cal();
			List<List<LottoItemEntity>> resultBlueList = reConvertToList(resultBlueIntList);
			System.out.println("src:" + src + " 蓝球组合种类:" + resultBlueList.size());
			LottoPrizeLevel lottoPrizeLevel = new LottoPrizeLevel();
			int count = 0;
			for(List<LottoItemEntity>  fRedList : resultRedList) {
				for(List<LottoItemEntity> fBlueList : resultBlueList) {
					LottoInfoEntity fInfoEntity = new LottoInfoEntity();
					fInfoEntity.redList = fRedList;
					fInfoEntity.blueList = fBlueList;
					int redHitCnt = cal(fInfoEntity.redList,targetEntity.redList);
					int blueHitCnt = cal(fInfoEntity.blueList,targetEntity.blueList);
					fInfoEntity.redHitCnt = redHitCnt;
					fInfoEntity.blueHitCnt = blueHitCnt;
					int level = calPrizeLevel(fInfoEntity);
					count++;
					switch(level) {
						case 1:
							lottoPrizeLevel.cLevelSuperCount++;
							break;
						case 2:
							lottoPrizeLevel.cLevelMidCount++;
							break;
						case 3:
							lottoPrizeLevel.cLevelThirdCount++;
							break;
						case 4:
							lottoPrizeLevel.cLevelForthCount++;
							break;
						case 5:
							lottoPrizeLevel.cLevelFifthCount++;
							break;
						case 6:
							lottoPrizeLevel.cLevelSixthCount++;
							break;
					}
				}//for
			}//for
			System.out.println("合计个数:" + count);
			lottoResultEntity.lottoLevel = lottoPrizeLevel;
		}else{
			System.out.println("单式算法");
			int redHitCnt = cal(srcEntity.redList,targetEntity.redList);
			srcEntity.redHitCnt = redHitCnt;
			int blueHitCnt = cal(srcEntity.blueList,targetEntity.blueList);
			srcEntity.blueHitCnt = blueHitCnt;
			showInfo(srcEntity);
			//计算几等奖
			int level = calPrizeLevel(srcEntity);
			if(level <= 0) {
				lottoResultEntity.status = LottoResultEntity.STATUS_NOT_HIT;
			}else {
				lottoResultEntity.status = LottoResultEntity.STATUS_HIT;
				LottoPrizeLevel prizeLevel = new LottoPrizeLevel();
				prizeLevel.level = level;
				lottoResultEntity.lottoLevel = prizeLevel;
			}
		}
		return lottoResultEntity;
	}
	
	private static final int calPrizeLevel(LottoInfoEntity entity) {
		int level = 0;
		int redHitCnt = entity.redHitCnt;
		int blueHitCnt = entity.blueHitCnt;
		if(redHitCnt == 5 && blueHitCnt == 2) {	//红色球命中5个，蓝色球命中2个
			level = 1;
		}else if(redHitCnt == 5 && blueHitCnt == 1) {	//红色球命中5个，蓝色球命中2个
			level = 2;
		}else if(redHitCnt == 5 && blueHitCnt == 0) {	//红色球命中5个，蓝色球命中0个
			level = 3;
		}else if(redHitCnt == 4 && blueHitCnt == 2) {	//红色球命中4个，蓝色球命中2个
			level = 3;
		}else if(redHitCnt == 4 && blueHitCnt == 1) {	//红色球命中4个，篮色球命中1个
			level = 4;
		}else if(redHitCnt == 3 && blueHitCnt == 2) {	//红色球命中3个，蓝色球命中2个
			level = 4;
		}else if(redHitCnt == 4 && blueHitCnt == 0) {	//红色球命中4个，篮色球命中0个
			level = 5;
		}else if(redHitCnt == 3 && blueHitCnt == 1) {	//红色球命中3个，篮色球命中1个
			level = 5;
		}else if(redHitCnt == 2 && blueHitCnt == 2) {	//红色球命中2个，蓝色球命中2个
			level = 5;
		}else if(redHitCnt == 3 && blueHitCnt == 0) {	//红色球命中3个，蓝色球命中0个
			level = 6;
		}else if(redHitCnt == 1 && blueHitCnt == 2) {	//红色球命中1个，蓝色球命中2个
			level = 6;
		}else if(redHitCnt == 2 && blueHitCnt == 1) {	//红色球命中2个，蓝色球命中1个
			level = 6;
		}else if(redHitCnt == 0 && blueHitCnt == 2) {	//红色球命中0个，蓝色球命中2个
			level = 6;
		}
		return level;
	} 
	
	/**
	 * 计算奖项级别 新规则
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unused")
	private static final int calPrizeLevelV2(LottoInfoEntity entity) {
		int level = 0;
		int redHitCnt = entity.redHitCnt;
		int blueHitCnt = entity.blueHitCnt;
		if(redHitCnt == 5 && blueHitCnt == 2) {	//红色球中5个，蓝色球中2个
			level = 1;
		}else if(redHitCnt == 5 && blueHitCnt == 1) {	//红色中5个，蓝色中2个
			level = 2;
		}else if(redHitCnt == 5 && blueHitCnt == 0) {	//红色中5个，蓝色中0个
			level = 3;
		}else if(redHitCnt == 4 && blueHitCnt == 2) {	//红色命中4个，蓝色命中2个
			level = 4;
		}else if(redHitCnt == 4 && blueHitCnt == 1) {	//红色命中4个，蓝色命中1
			level = 5;
		}else if(redHitCnt == 4 && blueHitCnt == 0) {	//红色命中4个，蓝色命中0个
			level = 6;
		}else if(redHitCnt == 3 && blueHitCnt == 2) {	//红色命中3个，蓝色命中2个
			level = 6;
		}else if(redHitCnt == 3 && blueHitCnt == 1) {	//红色命中3个，蓝色命中1
			level = 7;
		}else if(redHitCnt == 2 && blueHitCnt == 2) {	//红色命中2个，蓝色命中2个
			level = 7;
		}else if(redHitCnt == 3 && blueHitCnt == 0) {	//红色命中三个，蓝色命中0个
			level = 8;
		}else if(redHitCnt == 1 && blueHitCnt == 2) {	//红色命中1个，蓝色命中2个
			level = 8;
		}else if(redHitCnt == 2 && blueHitCnt == 1) {	//红色命中2个，蓝色命中1个
			level = 8;
		}else if(redHitCnt == 0 && blueHitCnt == 2) {	//红色命中0个，蓝色命中2个
			level = 8;
		}
		return level;
	}
	
	private static void showInfo(LottoInfoEntity entity) {
		List<LottoItemEntity> redList = entity.redList;
		List<LottoItemEntity> blueList = entity.blueList;
		System.out.println("========");
		System.out.println("红球 命中个数:" + entity.redHitCnt);
		for(LottoItemEntity itemEntity : redList) {
			System.out.print("num:" + itemEntity.num + " hit:" + itemEntity.isHit + "\t");
		}
		System.out.println();
		System.out.println("篮球命中个数:" + entity.blueHitCnt);
		for(LottoItemEntity itemEntity : blueList) {
			System.out.print("num:" + itemEntity.num + " hit:" + itemEntity.isHit + "\t");
		}
		System.out.println();
		System.out.println("========");
	}
	
	private static int cal(List<LottoItemEntity> srcList,List<LottoItemEntity> targetList) {
		int cnt = 0;
		for(LottoItemEntity itemEntity : srcList) {
			if(targetList.contains(itemEntity)) {
				itemEntity.isHit = true;
				cnt++;
			}
		}
		return cnt;
	}
	
	private static final boolean isLegal(String str) {
		boolean ok = false;
		if(!StringUtils.isEmpty(str)) {
			String[] array = str.split("\\|");
			if(array.length == 2) {
				String[] redList = array[0].split(",");
				String[] blueList = array[1].split(",");
				if(redList != null && redList.length >= 5) {
					if(blueList != null && blueList.length >= 2) {
						ok = true;
					}
				}
			}
		}
		return ok;
	}
	
	//02,10,13,16,19,23,25,26,28,30|01,02,03,04,05,09,10,11,12
	private static final LottoInfoEntity parseInfo(String src) {
		LottoInfoEntity lottoInfoEntity = new LottoInfoEntity();
		String[] array = src.split("\\|");
		String redStr = array[0];
		String blueStr = array[1];
		String[] redArray = redStr.split(",");
		String[] blueArray = blueStr.split(",");
		List<LottoItemEntity> redList = new ArrayList<LottoItemEntity>();
		for(int i = 0;i < redArray.length;i++) {
			String str = redArray[i];
			Integer num = Integer.valueOf(str);
			LottoItemEntity entity = new LottoItemEntity();
			entity.num = num;
			redList.add(entity);
		}
		lottoInfoEntity.redList = redList;
		
		List<LottoItemEntity> blueList = new ArrayList<LottoItemEntity>();
		for(int i = 0;i < blueArray.length;i++) {
			String str = blueArray[i];
			Integer num = Integer.valueOf(str);
			LottoItemEntity entity = new LottoItemEntity();
			entity.num = num;
			blueList.add(entity);
		}
		lottoInfoEntity.blueList = blueList;
		return lottoInfoEntity;
	}
	
	
}
