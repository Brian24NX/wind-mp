package com.iss.wind.common.util;

public class StrUtils {

    /**
     *  空判断 空：true；非空：false
     * @param s
     * @return
     */
    public static boolean isBlank(String s){
        return  (null == s || "".equals(s))? true : false;
    }

}
