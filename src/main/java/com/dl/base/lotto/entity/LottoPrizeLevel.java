package com.dl.base.lotto.entity;

/**
 * 中奖级别
 * @date 2019.01.15
 */
public class LottoPrizeLevel {
	//单式投注等级
	public int level;
	
	//复试投注
	public int cLevelSuperCount;
	public int cLevelMidCount;
	public int cLevelThirdCount;
	public int cLevelForthCount;
	public int cLevelFifthCount;
	public int cLevelSixthCount;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "level:" + level + " superCnt:" + cLevelSuperCount + " midCnt:" + cLevelMidCount + " thirdCnt:" + cLevelThirdCount + ""
				+ " forthCnt:" + cLevelForthCount + " fifthCnt:" + cLevelFifthCount + " sixCnt:" + cLevelSixthCount;
		return str;
	}
}
