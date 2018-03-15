package com.dl.base.result;

/**
 * 响应结果接口
 *
 * @create 2017/12/14 19:14
 **/
public interface Result {

    /**
     * 获取结果码
     *
     * @return 结果码
     */
    int getCode();

    /**
     * 获取结果提示信息
     *
     * @return 结果提示信息
     */
    String getMsg();
}
