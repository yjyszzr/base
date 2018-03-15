package com.dl.base.util;

import com.dl.base.model.Address;
import com.dl.base.context.BaseContextHandler;

/**
 * 会话管理工具类
 *
 * @create 2017/12/6 15:06
 **/
public class SessionUtil {

    private SessionUtil() {

    }

    /**
     * 获取当前用户ID
     *
     * @return 当前用户ID
     */
    public static Integer getUserId() {
        return BaseContextHandler.getUserID();
    }

    /**
     * 获取当前用户地址信息
     *
     * @return 地址信息
     */
    public static Address getAddress() {
        return BaseContextHandler.getAddress();
    }


}
