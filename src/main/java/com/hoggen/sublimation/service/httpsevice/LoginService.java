package com.hoggen.sublimation.service.httpsevice;

import com.hoggen.sublimation.dto.QuitDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface LoginService {

    /**
     * 判定登录是否成功
     */
    Map<String, Object> userLogin(String phone,String password);

    /**
     * 用户信息
     */
    Map<String, Object> userInfo(HttpServletRequest request);


    /**
     * 用户信息
     */
    Map<String, Object> quit(QuitDTO quitDTO);
}
