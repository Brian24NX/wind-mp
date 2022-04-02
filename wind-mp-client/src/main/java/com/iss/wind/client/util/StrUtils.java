package com.iss.wind.client.util;

public class StrUtils {

    /**
     *  空判断 空：true；非空：false
     * @param s
     * @return
     */
    public static boolean isBlank(String s){
        return  (null == s || "".equals(s))? true : false;
    }

    /**
     *  空对象判断
     * @param o
     * @return
     */
    public static Object handleNulRet(Object o){
        return  null == o ? new Object() : o;
    }

}
