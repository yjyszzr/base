package com.dl.base.exception;

import com.dl.base.result.Result;

/**
 * 统一异常
 *
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7732604485214567912L;
    /**
     * 异常编码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;


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

    /**
     * 构造方法
     *
     * @param code 异常编码
     * @param msg  异常提示信息
     */
    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造方法
     *
     * @param code  异常编码
     * @param msg   异常信息
     * @param cause 异常
     */
    public ServiceException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造异常信息
     *
     * @param result 结果信息
     */
    public ServiceException(Result result) {
        super(result.getMsg());
        this.code = result.getCode();
        this.msg = result.getMsg();
    }

    /**
     * 构造异常信息
     *
     * @param result 结果信息
     */
    public ServiceException(Result result, Throwable cause) {
        super(result.getMsg(), cause);
        this.code = result.getCode();
        this.msg = result.getMsg();
    }
}
