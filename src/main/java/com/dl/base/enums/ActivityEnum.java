package com.dl.base.enums;

public enum ActivityEnum {

	RegisterAct(0, "新手注册活动"),
    RechargeAct(1, "充值活动"),
    AppPromotion(2, "app推广活动");

	private Integer code;
	private String msg;

	private ActivityEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getName(Integer index) {
		for(ActivityEnum ae: ActivityEnum.values()) {
			if(ae.getCode().equals(index)) {
				return ae.getMsg();
			}
		}
		return null;
	}
	
	public static Integer getCode(String value) {
		for(ActivityEnum lwd: ActivityEnum.values()) {
			if(lwd.getMsg().equals(value)) {
				return lwd.getCode();
			}
		}
		return null;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
