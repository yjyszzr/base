package com.dl.base.context;


import java.util.HashMap;
import java.util.Map;

import com.dl.base.constant.CommonConstants;
import com.dl.base.model.Address;
import com.dl.base.model.UserDeviceInfo;

/**
 * Created by ace on 2017/9/8.
 */
public class BaseContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    public static Integer getUserID() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_ID);
        if (null != value) {
            return (Integer) value;
        }
        return null;
    }

    public static String getUsername() {
        Object value = get(CommonConstants.CONTEXT_KEY_USERNAME);
        return returnObjectValue(value);
    }


    public static String getName() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_NAME);
        return null == value ? "" : value.toString();
    }


    public static Address getAddress() {
        return (Address) get(CommonConstants.CONTEXT_KEY_USER_ADDRESS);
    }

    public static void setAddress(Address address) {
        set(CommonConstants.CONTEXT_KEY_USER_ADDRESS, address);
    }


    public static String getToken() {
        Object value = get(CommonConstants.CONTEXT_KEY_USER_TOKEN);
        return null == value ? "" : value.toString();
    }

    public static void setToken(String token) {
        set(CommonConstants.CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setName(String name) {
        set(CommonConstants.CONTEXT_KEY_USER_NAME, name);
    }

    public static void setUserID(String userID) {
        set(CommonConstants.CONTEXT_KEY_USER_ID, Integer.parseInt(userID));
    }

    public static void setUsername(String username) {
        set(CommonConstants.CONTEXT_KEY_USERNAME, username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static UserDeviceInfo getUserDeviceInfo() {
        return (UserDeviceInfo) get(CommonConstants.CONTEXT_KEY_USER_DEVICE);
    }

    public static void setAddress(UserDeviceInfo deviceInfo) {
        set(CommonConstants.CONTEXT_KEY_USER_DEVICE, deviceInfo);
    }
}
