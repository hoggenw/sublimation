package com.hoggen.sublimation.config.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class IPInterceptor implements HandlerInterceptor {

 //   @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//        PrintWriter out = null;//返回给页面显示
//        Map<String,Object> resultMap = new HashMap<String,Object>();
//        //取用户的真实IP
//        String ip  =  request.getHeader("x-forwarded-for");
//
//        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
//            ip = request.getHeader(" Proxy-Client-IP ");
//        }
//        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
//            ip = request.getHeader(" WL-Proxy-Client-IP ");
//        }
//        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        //取session中的IP对象(RequestIp新建的一个bean)
//        RequestIp re = (RequestIp) request.getSession().getAttribute(ip);
//        //第一次请求
//        if(null == re){
//            //放入到session中
//            RequestIp reIp = new RequestIp();
//            reIp.setCreateTime(System.currentTimeMillis());
//            reIp.setReCount(1);
//            request.getSession().setAttribute(ip,reIp);
//            return true;
//        }else{
//            Long createTime = re.getCreateTime();
//            if(null == createTime){
//                //时间请求为空
//                resultMap.put("code", 503);
//                resultMap.put("message", "请求太快，请稍后再试！");
//                out = response.getWriter();
//                JSONObject json = (JSONObject) JSON.toJSON(resultMap);
//                out.append(json.toString());
//                return false;
//            }else{
//                if(((System.currentTimeMillis() - createTime)/1000) > 3){
//                    System.out.println("通过请求！"+((System.currentTimeMillis() - createTime)/1000));
//                    //当前时间离上一次请求时间大于3秒，可以直接通过,保存这次的请求
//                    RequestIp reIp = new RequestIp();
//                    reIp.setCreateTime(System.currentTimeMillis());
//                    reIp.setReCount(1);
//                    request.getSession().setAttribute(ip,reIp);
//                    return true;
//                }else{
//                    //小于3秒，并且3秒之内请求了10次，返回提示
//                    if(re.getReCount() > 10){
//                        resultMap.put("code", 504);
//                        resultMap.put("message", "请求太快，请稍后再试！");
//                        out = response.getWriter();
//                        JSONObject json = (JSONObject) JSONObject.toJSON(resultMap);
//                        out.append(json.toString());//以json形式返回给页面，也可以直接返回提示信息
//                        return false;
//                    }else{
//                        //小于3秒，但请求数小于10次，给对象添加
//                        re.setCreateTime(System.currentTimeMillis());
//                        re.setReCount(re.getReCount()+1);
//                        request.getSession().setAttribute(ip,re);
//                        return true;
//                    }
//                }
//            }
//        }
//    }

}
