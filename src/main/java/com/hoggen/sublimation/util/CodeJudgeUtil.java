package com.hoggen.sublimation.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeJudgeUtil {

    public static boolean codeJudge(HttpServletRequest request,String code)
             {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if(code == null ||!code.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }

}
