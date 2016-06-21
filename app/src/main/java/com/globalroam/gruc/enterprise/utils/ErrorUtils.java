package com.globalroam.gruc.enterprise.utils;

/**
 * 错误定义类
 */
public class ErrorUtils {
    public static String errormessage(int resultcode){
        switch (resultcode){
            case 1011:
                return "手机号已存在";
            default:
                return "未知错误";
        }
    }
}
