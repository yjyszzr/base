package com.dl.base.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.phprpc.util.PHPSerializer;

/**
 * php序列化工具类
 *
 * @create 2017/11/30 11:50
 **/
@Slf4j
public final class PhpSerializeUtil {
    /**
     * php 序列化工具类
     */
    private PhpSerializeUtil() {
    }

    /**
     * 反序列化php字串为java对象
     *
     * @param phpObjStr php对象序列化后字符串
     * @return 反序列化后java对象
     */
    public static Object unserialize(String phpObjStr) {
        PHPSerializer phpSerializer = new PHPSerializer();
        try {
            Object obj = phpSerializer.unserialize(phpObjStr.getBytes());
            return obj;
        } catch (Exception e) {
            log.error("反序列化php字符串失败，字符串为:" + phpObjStr, e);
        }
        return null;
    }

    /**
     * 反序列化php字串为java对象
     *
     * @param phpObjStr php对象序列化后字符串
     * @return 反序列化后java对象
     */
    public static <T> T unserialize(String phpObjStr, Class<T> clazz) {
        PHPSerializer phpSerializer = new PHPSerializer();
        try {
            Object obj = phpSerializer.unserialize(phpObjStr.getBytes(), clazz);
            return (T) obj;
        } catch (Exception e) {
            log.error("反序列化php字符串失败，字符串为:" + phpObjStr + ", 反序列化后java对象类型为：" + clazz, e);
        }
        return null;
    }

    /**
     * 将java对象序列化为php序列话格式字符串
     *
     * @param obj 待序列化对象
     * @return 序列化后字符串
     */

    public static String serialize(Object obj) {
        PHPSerializer phpSerializer = new PHPSerializer();
        if (null == obj) {
            throw new NullPointerException();
        }
        try {
            byte[] serialize = phpSerializer.serialize(JSONObject.toJSON(obj));
            return new String(serialize);
        } catch (Exception e) {
            log.error("php序列化失败，序列化对象类型为：" + obj.getClass() + ", 对象为：" + obj, e);
            return null;
        }
    }
}
