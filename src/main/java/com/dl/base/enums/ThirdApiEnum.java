package com.dl.base.enums;

public enum ThirdApiEnum {
	
    RONG_BAO(1, "融宝"),
    YIN_HE(2, "银河"),
    JU_HE(3, "聚合"),
    ALI_DAYU(4, "阿里大于"),
    HE_NAN_LOTTERY(5, "河南出票"),
    XI_AN_LOTTERY(6, "西安出票"),
    CAI_XIAO_MI_LOTTERY(7, "彩小秘出票"),
    WEI_CAI_LOTTERY(8, "微彩时代出票"),
    SENDE_LOTTERY(9, "森德出票");


	private Integer code;
	private String msg;
	
	private ThirdApiEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getName(Integer index) {
		for(ThirdApiEnum lwd: ThirdApiEnum.values()) {
			if(lwd.getCode().equals(index)) {
				return lwd.getMsg();
			}
		}
		return null;
	}
	
	public static Integer getCode(String value) {
		for(ThirdApiEnum lwd: ThirdApiEnum.values()) {
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
