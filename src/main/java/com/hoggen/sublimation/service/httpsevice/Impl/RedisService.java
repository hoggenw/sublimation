package com.hoggen.sublimation.service.httpsevice.Impl;

import com.alibaba.fastjson.JSON;
import com.hoggen.sublimation.util.JsonUtil;
import com.hoggen.sublimation.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 一周有多少秒
     */
    private static final long FOREVER = 1000 * 24 * 60 * 60;

    private  static final String  header = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

    /**
     * 登录成功缓存信息
     * @Param userId
     * @Param token
     * @Author:hoggen
     * @Date:09:09 2019-11-19
     */
    public  String saveLoginStatus(String userId ,String token ){

        String returnString = null;
        String[] result = token.split("\\.");
        returnString = String.valueOf(result[2]);
        String midString =  String.valueOf(result[1]);
        if (set(userId,midString )){
            return  returnString;
        }
        return  returnString;

    }

    public  String getTokenStringForJudge(String userId ,String token ){

        String midString =  (String)get(userId);
        if (midString.length() <= 0 || midString == null){
            return "";
        }

        String returnString = header + "." + midString + "." + token;
        return  returnString;

    }

    public  boolean quitLogin(String userId ){

        return  delete(userId);

    }

    /**
     * 判断是否已经登录
     * @Param userId
     * @Param token
     * @Author:hoggen
     * @Date:09:09 2019-11-19
     */
    public  Boolean ifLogin(String userId,String token ){

        if (exists(userId)){
            String middleString =  String.valueOf(get(userId));
            if (middleString.length() <= 0){
                return  false;
            }

            String signToken = header+"."+middleString+"."+token;
            String signUserId = JwtUtil.getLoginUserID(signToken);

            return  signUserId.equals(userId);
        }else  {
            return  false;
        }





    }

    /**
     * 将 key，value 存放到redis数据库中，默认设置过期时间为1000天
     *
     * @param key
     * @param value
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value, FOREVER, TimeUnit.SECONDS);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean setJson(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(value), FOREVER, TimeUnit.SECONDS);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * 将 key，value 存放到redis数据库中，设置过期时间单位是秒
     *
     * @param key
     * @param value
     * @param expireTime
     */
    public boolean set(String key, Object value, long expireTime) {
        try {
            redisTemplate.opsForValue().set(key, String.valueOf(value) , expireTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 存入多组key value
     * @param map
     */
    public boolean set(Map<String,String> map) {
        try {
            redisTemplate.opsForValue().multiSet(map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 判断 key 是否在 redis 数据库中
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取与 key 对应的对象
     * @param key
     * @param clazz 目标对象类型
     * @param <T>
     * @return
     */
  /*  public <T> T get(String key, Class<T> clazz) {
        String s = get(key);
        if (s == null) {
            return null;
        }
        return JsonUtil.convertString2Obj(s, clazz);
    }*/

    /**
     * 获取 key 对应的字符串
     * @param key
     * @return
     */
    public Object get(String key) {
        return  redisTemplate.opsForValue().get(key);
    }

    public Object get(String key,Class clazz) {

        String value = (String) redisTemplate.opsForValue().get(key);
        return  JSON.parseObject(value,clazz);
    }

    /**
     * 删除 key 对应的 value
     * @param key
     */
    public boolean delete(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void setHash(String hashName,Map<String,String> map) {
        redisTemplate.opsForHash().putAll(hashName,map);
    }

    @Cacheable(cacheNames = "users",keyGenerator ="myKeyGenerator")
    public Map<Object,Object> getHash(String hashName){
        if (redisTemplate.hasKey(hashName)) {
            System.out.println(redisTemplate.opsForHash().entries(hashName));
            return redisTemplate.opsForHash().entries(hashName);
        }else {
            return null;
        }
    }
}
