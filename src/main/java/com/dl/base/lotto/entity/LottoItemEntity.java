package com.dl.base.lotto.entity;

public class LottoItemEntity {
	public Integer num;		//号码
	public boolean isHit;	//是否命中
	public boolean isDan;	//是否是加胆号
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		LottoItemEntity entity = (LottoItemEntity) obj;
		return this.num == entity.num;
	}
}
