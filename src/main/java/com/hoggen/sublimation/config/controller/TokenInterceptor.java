package com.hoggen.sublimation.config.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoggen.sublimation.dao.UserDao;
import com.hoggen.sublimation.entity.User;
import com.hoggen.sublimation.service.httpsevice.Impl.RedisService;
import com.hoggen.sublimation.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {


    @Autowired
    private RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean flag;
        String token = request.getHeader("token");
        String userId = request.getHeader("userId");

        // String token2 = HttpServletRequestUtil.getString(request, "token");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> modelMapData = new HashMap<String, Object>();
        // String token3 = (token == null) ? token2 : token;
        ObjectMapper mapper = new ObjectMapper();
        if (token == null || token.length() <= 0){
            modelMap.put("errno", "-10002");
            modelMap.put("errmsg", "未登录用户");
            modelMap.put("data", modelMapData);
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(mapper.writeValueAsString(modelMap));
            writer.close();
            response.flushBuffer();
            return false;
        }
        String realToken = redisService.getTokenStringForJudge(userId,token);
        String userid = JwtUtil.getLoginUserID(realToken);
        if ( !userid.equals(userId) ) {
            modelMap.put("errno", "-10001");
            modelMap.put("errmsg", "非法用户");
            modelMap.put("data", modelMapData);
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(mapper.writeValueAsString(modelMap));
            writer.close();
            response.flushBuffer();
            return false;
        }
        return true;
    }


}
