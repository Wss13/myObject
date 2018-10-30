/*
* @(#) RedisUtils.java 2018/09/18
*
* Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
*/
package com.example.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <code>RedisUtils</code>
 *
 * redicache 工具类
 *
 * @author    liumingchao
 * @version  v0.1 2018/09/18
 *
 * @see
 * @since    JDK1.8
 */
@SuppressWarnings("unchecked")
@Component
public class RedisUtils {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;


	/**
	 *
	 * 批量删除对应的value
	 *
	 * @param keys
	 * @return
	 * @exception
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 *
	 * 批量删除key
	 *
	 * @param pattern
	 * @return
	 * @exception
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0){
			redisTemplate.delete(keys);
		}
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 * @return
	 * @exception
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		Object result = null;
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		if (result == null) {
			return null;
		}
		return result.toString();
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean hmset(String key, Map<String, String> value) {
		boolean result = false;
		try {
			redisTemplate.opsForHash().putAll(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, String> hmget(String key) {
		Map<String, String> result = null;
		try {
			result = redisTemplate.opsForHash().entries(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 从redis中取出值
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field){
		if(key == null || "".equals(key)){
			return null;
		}
		return stringRedisTemplate.opsForValue().get(key);
	}
	//从左边添加
	public boolean leftPush(String key, String value) {
		boolean result = false;
		if(key==null||value==null|| "".equals(key)){
			return result;
		}
		try {
			stringRedisTemplate.opsForList().leftPush(key,value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//list从左边抛出
	public String listLeftPop(String key) {
		String result = "";
		if(key==null|| "".equals(key)){
			return result;
		}
		try {
			result = stringRedisTemplate.opsForList().leftPop(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//list从右边抛出
	public String listRightPop(String key) {
		String result = "";
		if(key==null|| "".equals(key)){
			return result;
		}
		try {
			result = stringRedisTemplate.opsForList().rightPop(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//设置redis key值得过期时间
	public boolean setKeyTime(String key,long time,TimeUnit timeUnit){
		boolean result = false;
		if(key==null|| "".equals(key)){
			return result;
		}
		try {
			stringRedisTemplate.opsForValue().set("id"+key,key,time,timeUnit);
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void stringRemove(final String key) {
		if (stringExists(key)) {
			redisTemplate.delete(key);
		}
	}
	//key为prefix 得长度
	public long prefixKeysSize(String prefix){
		long result = 0L;
		if(prefix==null|| "".equals(prefix)){
			return result;
		}
		return stringRedisTemplate.keys(prefix).size();
	}
	//批量去除key
	public void stringRemove(final String... keys) {
		if(keys.length==0){
			return;
		}
		for (String key : keys) {
			stringRemove(key);
		}
	}
	//判断redis 是否存在key
	public boolean stringExists(final String key) {
		boolean result = false;
		if(key==null|| "".equals(key)){
			return result;
		}
		return stringRedisTemplate.hasKey(key);
	}
	//获取list的长度
	public Long getListSize(String key){
		Long result = 0L;
		if(key==null|| "".equals(key)){
			return result;
		}
		try {
			result = stringRedisTemplate.opsForList().size(key);
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//获取list第几个的值
	public String getListIndex(String key,Long index){
		String result = "";
		if(key==null||index==null|| "".equals(key)){
			return result;
		}
		try {
			result = stringRedisTemplate.opsForList().index(key,index);
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//list去除相同的value
	public boolean listRemove(String key, String value) {
		boolean result = false;
		if(key==null|| "".equals(key)||value==null){
			return result;
		}
		try {
			stringRedisTemplate.opsForList().remove(key,1,value);
			result = true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//添加set的值
	public boolean setAdd(String key, String value) {
		boolean result = false;
		if(key==null|| "".equals(key)||value==null){
			return result;
		}
		try {
			stringRedisTemplate.opsForSet().add(key,value);
			result = true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//去除Set的值
	public boolean setRemove(String key, String value) {
		boolean result = false;
		if(key==null|| "".equals(key)||value==null){
			return result;
		}
		try {
			stringRedisTemplate.opsForSet().remove(key,value);
			result = true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//随机返回set的值1
	public boolean setRandomMember(String key) {
		boolean result = false;
		if(key==null|| "".equals(key)){
			return result;
		}
		try {
			stringRedisTemplate.opsForSet().randomMember(key);
			result = true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}