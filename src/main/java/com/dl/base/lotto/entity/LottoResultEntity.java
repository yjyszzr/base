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
	public LottoPrizeLevel lottoLevel;	//几等奖
}
