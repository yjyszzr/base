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
	public int cLevelSevenCount;
	public int cLevelEightCount;
	public int cLevelNineCount;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "level:" + level + " superCnt:" + cLevelSuperCount + " midCnt:" + cLevelMidCount + " thirdCnt:" + cLevelThirdCount + ""
				+ " forthCnt:" + cLevelForthCount + " fifthCnt:" + cLevelFifthCount + " sixCnt:" + cLevelSixthCount + " sevenCnt:" + cLevelSevenCount + 
				" eightCnt:" + cLevelEightCount + " nineCnt:" + cLevelNineCount;
		return str;
	}
	
	public boolean isCompoundHit() {
		boolean isHit = false;
		if(cLevelSuperCount > 0 || cLevelMidCount > 0 || cLevelThirdCount > 0 || cLevelForthCount > 0 || cLevelFifthCount > 0  
		  || cLevelSixthCount > 0 || cLevelSevenCount > 0 || cLevelEightCount > 0 || cLevelNineCount > 0) {
			isHit = true;
		}
		return isHit;
	}
	
	/**
	 * 输出奖级别
	 * @return
	 */
	public String toCompoundPrizeLevle() {
		StringBuilder builder = new StringBuilder();
		if(cLevelSuperCount > 0) {
			builder.append(cLevelSuperCount+"A"+"+");
		}
		if(cLevelMidCount > 0) {
			builder.append(cLevelMidCount+"B" + "+");
		}
		if(cLevelThirdCount > 0) {
			builder.append(cLevelThirdCount + "C" + "+");
		}
		if(cLevelForthCount > 0) {
			builder.append(cLevelForthCount + "D" + "+");
		}
		if(cLevelFifthCount > 0) {
			builder.append(cLevelFifthCount + "E" + "+");
		}
		if(cLevelSixthCount > 0) {
			builder.append(cLevelSixthCount + "F" + "+");
		}
		if(cLevelSevenCount > 0) {
			builder.append(cLevelSevenCount+"G" + "+");
		}
		if(cLevelEightCount > 0) {
			builder.append(cLevelEightCount+"H" + "+");
		}
		if(cLevelNineCount > 0) {
			builder.append(cLevelNineCount + "I" + "+");
		}
		if(builder.length() > 0) {
			char c = builder.charAt(builder.length() - 1);
			if(c == '+') {
				builder.deleteCharAt(builder.length() - 1);
			}
		}
		return builder.toString();
	}
}
