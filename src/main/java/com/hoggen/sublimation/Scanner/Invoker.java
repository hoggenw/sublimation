package com.hoggen.sublimation.Scanner;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 命令执行器
 *
 */

public class Invoker {


    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * 目标对象
     *
     */

    private Object target;


    /**
     * 方法
     */
    private Method method;


    public static Invoker valueOf(Method method, Object target){
        Invoker invoker = new Invoker();
        invoker.setMethod(method);
        invoker.setTarget(target);
        return invoker;
    }


    public  Object invoke(Object... paramValues){
        try {
            return  method.invoke(target,paramValues);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return  null;
    }


}













