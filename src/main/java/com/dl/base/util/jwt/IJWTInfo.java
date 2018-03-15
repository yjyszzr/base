package com.dl.base.util.jwt;

/**
 * Created by ace on 2017/9/10.
 */
public interface IJWTInfo {
    /**
     * 获取用户名
     *
     * @return
     */
    String getUnique();

    /**
     * 获取用户ID
     *
     * @return
     */
    String getUserId();
}
