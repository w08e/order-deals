package com.zhouyi.order.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtils {

    @Resource(name = "redis")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, Long time) {

        if (StringUtil.isBlank(key) || value == null) {
            return false;
        }

        try {
            setCache(key, value, time);
            return true;

        } catch (Exception e) {
            log.error("set cache error", e);
            return false;
        }
    }

    private void setCache(String key, Object value, Long time) {

        // 默认 1小时
        long expireTime = 3600L;
        if (time != null && time > 0) {
            expireTime = time;
        }

        redisTemplate.opsForValue()
                .setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);

    }


    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(List.of(key));
            }
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存获取
     *
     * @return 值
     */
    public ValueOperations<String, Object> opsForValue() {
        return redisTemplate.opsForValue();
    }

    /**
     * 普通缓存获取
     *
     * @return 值
     */
    public SetOperations<String, Object> opsForSet() {
        return redisTemplate.opsForSet();
    }

}
