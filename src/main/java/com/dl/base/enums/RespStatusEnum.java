package com.dl.base.enums;

import com.dl.base.result.Result;

public enum RespStatusEnum implements Result {
    SUCCESS(0, ""),
    FAIL(1, "操作失败"),
    BAD_REQUEST(400, "参数异常"),
    FORBIDDEN(403, "没有访问权限"),
    SERVER_ERROR(500, "服务器开小差"),
    NOT_FOUND(404, "接口不存在"),
    NEED_LOGIN(600, "请先登录"),
    TOKEN_EXPIRE(501, "token过期"),
    UNAUTHORIZED(401, "未认证");

    private int status;
    private String msg;

    RespStatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int getCode() {
        return status;
    }

    @Override
    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }
}
