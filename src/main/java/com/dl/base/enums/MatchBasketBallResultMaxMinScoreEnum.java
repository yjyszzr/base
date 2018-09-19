package com.dl.base.enums;

public enum MatchBasketBallResultMaxMinScoreEnum {
	
	MAX_SCORE("1", "大分"),
	MIN_SCORE("2", "小分");
	
	private String code;
	private String msg;
	
	private MatchBasketBallResultMaxMinScoreEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getName(String index) {
		for(MatchBasketBallResultMaxMinScoreEnum lwd: MatchBasketBallResultMaxMinScoreEnum.values()) {
			if(lwd.getCode().equals(index)) {
				return lwd.getMsg();
			}
		}
		return null;
	}
	
	public static String getCode(String value) {
		for(MatchBasketBallResultMaxMinScoreEnum lwd: MatchBasketBallResultMaxMinScoreEnum.values()) {
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
