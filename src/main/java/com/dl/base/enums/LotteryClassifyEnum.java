package com.dl.base.enums;


public enum LotteryClassifyEnum {

	JC_FOOTBALL(1,"竞彩足球"),
	SUPER_LOTTO(2,"大乐透"),
	JC_BASKETBALL(3,"竞彩篮球"),
	KUAI3(4,"快3"),
	DOUBLE_BALL(5,"双色球"), 
	BJ_SINGLE(6,"北京单场"), 
	GD_5IN11(7,"广东11选5"),
	MORE_L(8,"更多彩种");
	
	private Integer code;
    private String msg;

	public static String getMsgByCode(Integer code) {
		for(LotteryClassifyEnum ml: LotteryClassifyEnum.values()) {
			if(ml.getcode() == code) {
				return ml.getMsg();
			}
		}
		return "";
	}
    
    
    private LotteryClassifyEnum(int code, String msg) {
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
