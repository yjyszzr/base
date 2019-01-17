package com.dl.base.lotto.entity;

/**
 * 大乐透返回结果
 * @author
 * @data 2019.01.15
 */
public class LottoResultEntity {
	public static final int STATUS_NOT_ILLEGAL = 1;	//非法号码组合
	public static final int STATUS_HIT = 2;			//号码命中
	public static final int STATUS_NOT_HIT = 3;		//号码未命中
	
	public int status;	//状态
	public String reason;
	public LottoPrizeLevel lottoLevel;	//单式几等奖
	public boolean isCompund;	//是否是复试
	
	public int getMaxLevel() {
		int maxLevel = 0;
		if(lottoLevel != null) {
			if(!isCompund) {
				maxLevel = lottoLevel.level;
			}else {
				if(lottoLevel.cLevelSuperCount > 0) {
					maxLevel = 1;
				}else if(lottoLevel.cLevelMidCount > 0) {
					maxLevel = 2;
				}else if(lottoLevel.cLevelThirdCount > 0) {
					maxLevel = 3;
				}else if(lottoLevel.cLevelForthCount > 0) {
					maxLevel = 4;
				}else if(lottoLevel.cLevelFifthCount > 0) {
					maxLevel = 5;
				}else if(lottoLevel.cLevelSixthCount > 0) {
					maxLevel = 6;
				}
			}
		}
		return maxLevel;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "status:" + status + " reason:" + reason + " isCompund:" + isCompund + " lottoLevel:" + lottoLevel;
		return str;
	}
}
