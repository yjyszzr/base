package com.dl.base.result;

import com.dl.base.enums.RespStatusEnum;
import com.dl.base.exception.ServiceException;

import static com.dl.base.enums.RespStatusEnum.*;

/**
 * 响应结果生成类
 */
public class ResultGenerator {

    public static <T> BaseResult<T> genSuccessResult() {
        return genResult(SUCCESS, null, null);
    }

    public static <T> BaseResult<T> genSuccessResult(String message) {
        return genResult(SUCCESS, message, null);
    }

    public static <T> BaseResult<T> genSuccessResult(String message, T data) {
        return genResult(SUCCESS, message, data);
    }


    public static <T> BaseResult<T> genFailResult(String message) {
        return genResult(FAIL, message, null);
    }

    public static <T> BaseResult<T> genFailResult(String message, T data) {
        return genResult(FAIL, message, data);
    }

    public static <T> BaseResult<T> genFailResult() {
        return genFailResult(null);
    }

    public static <T> BaseResult<T> genNeedLoginResult(String message) {
        return genResult(NEED_LOGIN, message, null);
    }

    public static <T> BaseResult<T> genNeedLoginResult(String message, T data) {
        return genResult(NEED_LOGIN, message, data);
    }

    public static <T> BaseResult<T> genBadRequestResult(String message) {
        return genResult(BAD_REQUEST, message, null);
    }

    public static <T> BaseResult<T> genBadRequestResult(String message, T data) {
        return genResult(BAD_REQUEST, message, data);
    }

    public static <T> BaseResult<T> genForbiddenResult(String message) {
        return genResult(FORBIDDEN, message, null);
    }

    public static <T> BaseResult<T> genForbiddenResult(String message, T data) {
        return genResult(FORBIDDEN, message, data);
    }

    public static <T> BaseResult<T> genServerErrorResult(String message) {
        return genResult(SERVER_ERROR, message, null);
    }

    public static <T> BaseResult<T> genServerErrorResult(String message, T data) {
        return genResult(SERVER_ERROR, message, data);
    }

    public static <T> BaseResult<T> genNotFoundResult(String message) {
        return genResult(NOT_FOUND, message, null);
    }

    public static <T> BaseResult<T> genNotFoundResult(String message, T data) {
        return genResult(NOT_FOUND, message, data);
    }

    public static <T> BaseResult<T> genUnauthorizedResult(String message) {
        return genResult(UNAUTHORIZED, message, null);
    }

    public static <T> BaseResult<T> genUnauthorizedResult(String message, T data) {
        return genResult(UNAUTHORIZED, message, data);
    }


    public static <T> BaseResult<T> genResult(RespStatusEnum status, String message, T data) {
        if (message == null || message.isEmpty()) {
            message = status.getMsg();
        }
        return new BaseResult<>(status, data).setMsg(message);
    }

    /**
     * 根据系统异常生成相应信息
     *
     * @param e   异常信息
     * @param <T> 泛型
     * @return 返回信息
     */
    public static <T> BaseResult<T> genResult(ServiceException e) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(e.getCode());
        baseResult.setMsg(e.getMsg());
        return baseResult;
    }

    /**
     * 生成响应结果
     *
     * @param code 编码
     * @param msg  提示信息
     * @param <T>  泛型
     * @return 结果
     */
    public static <T> BaseResult<T> genResult(Integer code, String msg) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setMsg(msg);
        baseResult.setCode(code);
        return baseResult;
    }

}
