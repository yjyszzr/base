package com.dl.base.enums;

public enum BasketBallMatchResultHILOEnum {
	
	HILO_LEV1("1", "1-5分"), 
	HILO_LEV2("2", "6-10分"),
	HILO_LEV3("3", "11-15分"),
	HILO_LEV4("4", "16-20分"),
	HILO_LEV5("5", "21-25分"),
	HILO_LEV6("6", "26分+");

	
	private String code;
	private String msg;
	
	private BasketBallMatchResultHILOEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getName(String index) {
		for(BasketBallMatchResultHILOEnum lwd: BasketBallMatchResultHILOEnum.values()) {
			if(lwd.getCode().equals(index)) {
				return lwd.getMsg();
			}
		}
		return null;
	}
	
	public static String getCode(String value) {
		for(BasketBallMatchResultHILOEnum lwd: BasketBallMatchResultHILOEnum.values()) {
			if(lwd.getMsg().equals(value)) {
				return lwd.getCode();
			}
		}
		return null;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
