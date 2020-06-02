package com.hoggen.sublimation.service.httpsevice.Impl;

import com.hoggen.sublimation.service.httpsevice.BaseService;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Service
public class BaseServiceImpl implements BaseService {
    private Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    private BaseServiceMethod baseServiceMethod;

    @Override
    public Map<String, Object> call(Map<String, Object> map) {

        log.info("========ywqService========"+map);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String method = (String) map.get("method");
            Method m = BaseServiceMethod.class.getDeclaredMethod(method, Map.class);
            if(null != m){
                return (Map<String, Object>) m.invoke(baseServiceMethod, map);
            }
            resultMap.put("result", "fail");
            resultMap.put("info", "not such method");
        } catch (InvocationTargetException ie) {
            // 反射，方法执行异常。则获取方法的异常信息
            Throwable tw = ie.getTargetException();
            resultMap.put("result", "error");
            resultMap.put("info", tw.getMessage());
            log.error("ywqService call反射，执行方法出现异常", ie);
        } catch (Exception e) {
            resultMap.put("result", "error");
            resultMap.put("info", "出现异常：" + e.getMessage());
            log.error("ywqService call出现异常", e);
        }
        return resultMap;
    }
}
