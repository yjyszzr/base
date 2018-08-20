package com.dl.base.util;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 通过redis单线程的特性提供给并发操作的锁
 * @author zzr
 *
 */
public class RedisLockUtil {
	
	/**
	 * absent == true 缺少key
	 * absent == false 已经存在key
	 * @param stringRedisTemplate
	 * @param key
	 * @return
	 */
	public static Boolean lockRedisThread(StringRedisTemplate stringRedisTemplate,String key) {
		Boolean absent = stringRedisTemplate.opsForValue().setIfAbsent(key, "on");
		return absent;
	}
	
	/**
	 * 删除key
	 * @param stringRedisTemplate
	 * @param key
	 */
	public static void unlockRedisThread(StringRedisTemplate stringRedisTemplate,String key) {
		stringRedisTemplate.delete(key);
	}	

}
