package com.modelsystem.ext.redis;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by bingchenglin on 2017/5/3.
 */
@Component("redisUtil")
public class RedisUtil {

    private Logger log = Logger.getLogger(RedisUtil.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static final String BASE_GROUP = "system_data";

    @Autowired
    private StringRedisTemplate redisTemplate;//redis操作模板


    public void put(String group,String key, String value,Integer expireTime) {
        if (key==null || "".equals(key)) {
            return;
        }
        redisTemplate.opsForHash().put(group, key, value);
        if(expireTime!=null){
            redisTemplate.boundValueOps(key).expire(expireTime, TimeUnit.DAYS);
        }
    }


    public void put(String group,String key, Object value,Integer expireTime) {
        if (key==null || "".equals(key)) {
            return;
        }
        try {
            redisTemplate.opsForHash().put(group, key, MAPPER.writeValueAsString(value));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(expireTime!=null){
            redisTemplate.boundValueOps(key).expire(expireTime, TimeUnit.DAYS);
        }

    }


    public <T> T get(String group,String key, Class<T> className) {
        Object obj = redisTemplate.opsForHash().get(group, key);
        if(obj == null){
            return null;
        }
        T t = null;
        try {
            t = MAPPER.readValue("" + obj, className);
        } catch (IOException e) {
            log.error("json字符串转对象转换失败",e);
        }
        return t;
    }


    public String get(String group,String key) {
        Object obj = redisTemplate.opsForHash().get(group, key);
        if(obj == null){
            return null;
        }else{
            return String.valueOf(obj);
        }
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
