package com.dl.base.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BetUtils {


	public static void main(String[] args) {
//		System.out.println(getCombinationCount(4,2));
//		System.out.println(getLettoBetNum("01,02,03,04,05,06|01,02,03"));
		System.out.println(maxTcBetTime(new BigDecimal("3.0"),100));
	}
	/**
	 * 体彩最高投注金额为20000,最大倍数是99倍
	 * @return
	 */
	final static public int maxTcBetTime(BigDecimal oneTicketMoney,int betNum){
		BigDecimal maxMoney = new BigDecimal("20000");
		BigDecimal maxTimesBigDe = maxMoney.divideToIntegralValue(oneTicketMoney).divideToIntegralValue(new BigDecimal(""+betNum));
		int maxTimes=maxTimesBigDe.intValue();
		if(maxTimes>99){
			maxTimes=99;
		}
		return maxTimes;
	}
	/**
	 * 
	 * @param betType 00单式,01复式 02 胆拖
	 * @param betContent 单式举例:
	 * 格式01,02,03,04,05|01,02 
	 * 复式格式:01,02,03,04,05,06|01,02,03 
	 * 胆拖:01,02$03,04,05,06|01$03,04܄ݸ
	 * @return
	 */
	 final static public int getLettoBetNum(String betContent){
//		 获取胆拖等信息
		 String[] betArr = betContent.split("\\|");
		 String betPre = betArr[0];
		 String betSuf = betArr[1];
		 int redDanNumCount = getDanNumberCount(betPre,Boolean.TRUE);
		 int redTuoNumCount = getDanNumberCount(betPre,Boolean.FALSE);
		 int blueDanNumCount = getDanNumberCount(betSuf,Boolean.TRUE);
		 int blueTuoNumCount = getDanNumberCount(betSuf,Boolean.FALSE);
		 return getLettoBetNum(redTuoNumCount,redDanNumCount,blueTuoNumCount,blueDanNumCount);
	 }
	 private static int getDanNumberCount(String betNumberStr,Boolean trueIsDanAndFalseIsTuo) {
		 int numberCount=0;
		 if(betNumberStr.contains("$")){
			 String[] betNumArr = betNumberStr.split("\\$");
			 if(trueIsDanAndFalseIsTuo){
				 numberCount = betNumArr[0].split(",").length;	 
			 }else{
				 numberCount = betNumArr[1].split(",").length;
			 }
		 }else{
			 if(!trueIsDanAndFalseIsTuo){
				 numberCount = betNumberStr.split(",").length;
			 }
		 }
		return numberCount;
	}
	final static public int getLettoBetNum(int rt,int rd,int bt,int bd){
		int betNumR = getCombinationCount(rt, 5-rd);
		int betNumB = getCombinationCount(bt, 2-bd);
		return betNumR*betNumB;
	 }
	/**
	 * 
	 * @描述:求复式注数（大乐透）
	 * @param r 红球
	 * @param b 蓝球
	 * @return
	 */
	final static private int getCathecticsCount(int r,int b){
		int betNumR = getCombinationCount(r, 5);
		int betNumB = getCombinationCount(b, 2);
		return betNumR*betNumB;
	}
	/**
	 * 
	 * @描述:求胆拖注数（大乐透）
	 * @param rt 红拖
	 * @param rd 红胆
	 * @param bt 蓝拖
	 * @param bd 蓝胆
	 * @return
	 */
	final static public int getDanTuoCathecticsCount(int rt,int rd,int bt,int bd){
		int betNumR = getCombinationCount(rt, 5-rd);
		int betNumB = getCombinationCount(bt, 2-bd);
		return betNumR*betNumB;
	}
	
	/**
	 * @作者:andy
	 * @描述:求组合C(n,r)
	 * @param n
	 * @param r
	 * @return
	 */
	final static private int getCombinationCount(int n,int r){
		if(r > n) return 0;
		if(r < 0 || n < 0) return 0;
		return getFactorial(n).divide(getFactorial(r),BigDecimal.ROUND_HALF_DOWN).divide(getFactorial((n-r)),BigDecimal.ROUND_HALF_DOWN).intValue();
	}
	/**
	 * @作者:andy
	 * @描述:求n的阶乘
	 * @param n
	 * @return
	 */
	final static private BigDecimal getFactorial(int num) {
        BigDecimal sum = new BigDecimal(1.0);
        for(int i = 1; i <= num; i++)
        {
        	BigDecimal a = new BigDecimal(new BigInteger(i+""));
            sum =sum.multiply(a);
        }
        return sum;
    }
}
