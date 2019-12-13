package com.hoggen.sublimation.util;

import java.util.HashMap;
import java.util.Map;

public class ResponedUtils {
    /**
     * 统一返回数据格式
     * @param code	错误码
     * @param data	返回的数据
     * @return
     */
    public static Map<String, Object> returnCode(Integer code, String errmsg, Object data){
        Map<String,Object> map = new HashMap<String,Object>();

        map.put("errno", code);
        map.put("errmsg", errmsg);
        map.put("data", data);
        return map;
    }
}
