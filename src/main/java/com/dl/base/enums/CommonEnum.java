package com.dl.base.enums;

public enum CommonEnum {
	
	//文章分类
	CONTENT_CAT_TODAY_FOCUS(1, "今日关注"),
	CONTENT_CAT_PREFORCAST(2, "竞彩预测"),
	CONTENT_CAT_ANALYSIS(3, "牛人分析"),
	CONTENT_CAT_OTHER(4, "其他");

	
	private Integer code;
	private String msg;
	
	private CommonEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getName(Integer index) {
		for(CommonEnum lwd: CommonEnum.values()) {
			if(lwd.getCode().equals(index)) {
				return lwd.getMsg();
			}
		}
		return null;
	}
	
	public static Integer getCode(String value) {
		for(CommonEnum lwd: CommonEnum.values()) {
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
