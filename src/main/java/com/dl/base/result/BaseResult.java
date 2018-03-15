package com.dl.base.result;


import com.alibaba.fastjson.annotation.JSONField;
import com.dl.base.enums.RespStatusEnum;
import com.dl.base.util.JSONHelper;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 *
 * @author new
 */
public class BaseResult<T> implements Result, Serializable {
    private static final long serialVersionUID = -5384184441407189390L;
    private String msg;
    private T data;

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    private int code;

    public BaseResult() {
    }

    public BaseResult(RespStatusEnum resultCode) {
        new BaseResult(resultCode, null);
    }

    public BaseResult(RespStatusEnum resultCode, T data) {
        this.code = resultCode.getStatus();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public BaseResult<T> setMsg(String message) {
        this.msg = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResult setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 判断接口返回是否为成功
     *
     * @return 是否成功
     */
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return 0 == code;
    }


    @Override
    public String toString() {
        return JSONHelper.bean2json(this);
    }
}
