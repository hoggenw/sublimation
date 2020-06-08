package com.hoggen.sublimation.config.service;

import com.hoggen.sublimation.config.redis.JedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private JedisCache jedisCache;

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
        if (result.length < 3){
            return  returnString;
        }
        returnString = String.valueOf(result[2]);
        if (set(userId,returnString )){
            return  returnString;
        }
        return  null;

    }

    public  String getTokenStringForJudge(String userId ){

        String returnString =  (String)get(userId);
        if ( returnString == null){
            return "";
        }
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
            String saveToken =  String.valueOf(get(userId));
            if (saveToken.length() <= 0){
                return  false;
            }
            return  token.equals(saveToken);
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
            jedisCache.set(key, value);

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
        return jedisCache.existKey(key);
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
        return  jedisCache.get(key);
    }



    /**
     * 删除 key 对应的 value
     * @param key
     */
    public boolean delete(String key) {
        try {
            jedisCache.delKey(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}
