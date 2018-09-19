package com.dl.base.enums;


public enum MatchBasketPlayTypeEnum {
	PLAY_TYPE_HHAD(1,"MNL"), //胜负
	PLAY_TYPE_HAD(2,"HDC"), // 让分胜负
	PLAY_TYPE_CRS(3,"WNM"), //胜分差
	PLAY_TYPE_TTG(4,"HILO"); //大小分
	
	
	private Integer code;
    private String msg;

	public static String getMsgByCode(Integer code) {
		for(MatchBasketPlayTypeEnum ml: MatchBasketPlayTypeEnum.values()) {
			if(ml.getcode() == code) {
				return ml.getMsg();
			}
		}
		return "";
	}
    
    
    private MatchBasketPlayTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getcode() {
        return code;
    }

    public void setcode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
