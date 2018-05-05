package com.dl.base.enums;

public enum AccountEnum {
	
	
    ALIPAY(0, "支付宝支付"),
    WEIXINPAY(1, "微信支付"),
    YUEPAY(2, "余额支付"),
    MIXPAY(3, "混合支付"),
    RONGBAOPAY(4, "融宝支付"),
    PAY_ROLLBACK(5, "资金已退回"),
	REWARD(6, "派奖"),
	WITHDRAW(7, "提现");


	private Integer code;
	private String msg;
	
	private AccountEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static String getName(Integer index) {
		for(AccountEnum lwd: AccountEnum.values()) {
			if(lwd.getCode().equals(index)) {
				return lwd.getMsg();
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

}
