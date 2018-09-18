package com.honor.sbmb.base.redis.impl;

import com.honor.sbmb.base.redis.RedisDao;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by xiagz
 * Date:2018/7/17
 */
@Repository
public class RedisDaoImpl implements RedisDao,Cache {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public boolean set(String key, Object value) {
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

    @Override
    public boolean set(String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Object get(String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    @Override
    public boolean remove(String key) {
        boolean result = false;
        try {
            if (exists(key)) {
                redisTemplate.delete(key);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void resetExpireTime(String key, Long expireTime) {
        try {
            if (exists(key)) {
                redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object o, Object o1) {

    }

    @Override
    public Object getObject(Object o) {
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
