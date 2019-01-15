package com.dl.base.lotto.entity;

import java.util.List;

/**
 * 投注信息
 * @author
 * @
 */
public class LottoInfoEntity{
	/**
	 * 红球
	 */
	public List<LottoItemEntity> redList;
	
	/**
	 * 篮球
	 */
	public List<LottoItemEntity> blueList;

	/**
	 * 红色球命中个数
	 */
	public int redHitCnt;
	
	/**
	 * 蓝色球命中个数
	 */
	public int blueHitCnt;
}
