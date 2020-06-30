package com.hoggen.sublimation.config.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class IPInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        PrintWriter out = null;//返回给页面显示
        Map<String,Object> resultMap = new HashMap<String,Object>();
        //取用户的真实IP
        String ip  =  request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.info("ip地址" + ip);
        List<String> ipStrings =new ArrayList<String>() {//这个大括号 就相当于我们  new 接口
            {
                add("192.168.1.16");
                add("49.235.149.115");
            }
        };
        boolean result = false;
        for (String whiteIp:ipStrings) {
            if (whiteIp.equals(ip)){
                return true;
            }
        }

        return  result;


    }

}
