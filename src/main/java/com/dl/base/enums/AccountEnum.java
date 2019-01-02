package com.dl.base.enums;

public enum AccountEnum {
	
	//1-奖金 2-充值 3-购彩 4-提现 5-红包 6-账户回滚
	REWARD(1, "奖金","奖"),
    RECHARGE(2, "充值","充"),
    BUYPAY(3, "购彩","购"),
    WITHDRAW(4, "提现","提"),
    BONUS(5, "红包","红"),
    MONEY_ROLLBACK(6, "资金已退回","退"),
	BEYOND_ENTER_MONEY(9, "输入过多金额","误");

	private Integer code;
	private String msg;
	private String shortStr;
	
	private AccountEnum(Integer code, String msg,String shortStr) {
		this.code = code;
		this.msg = msg;
		this.shortStr = shortStr;
	}

	public static String getName(Integer index) {
		for(AccountEnum ae: AccountEnum.values()) {
			if(ae.getCode().equals(index)) {
				return ae.getMsg();
			}
		}
		return null;
	}
	
	public static String getShortStr(Integer index) {
		for(AccountEnum ae: AccountEnum.values()) {
			if(ae.getCode().equals(index)) {
				return ae.getShortStr();
			}
		}
		return null;
	}
	
	public static Integer getCode(String value) {
		for(AccountEnum lwd: AccountEnum.values()) {
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
	
	public void setShortStr(String shortStr) {
		this.shortStr = shortStr;
	}
	
	public String getShortStr() {
		return shortStr;
	}

}
