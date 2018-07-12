package com.stuframework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类型转换工具
 */
public final class CastUtil {

    private static Logger log = LoggerFactory.getLogger(CastUtil.class);


    public static String castString(Object obj){
        return castString(obj,"");
    }

    public static String castString(Object obj, String defaultValue){
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static double castDouble(Object obj){
        return castDouble(obj,0);
    }

    public static double castDouble(Object obj,double defaultValue){
        double doubleValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(!StringUtil.isEmpty(strValue)){
                try {
                    defaultValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    defaultValue = doubleValue;
                    log.error("解析{}为double类型失败",strValue);
                }
            }
        }
        return doubleValue;
    }

    public static long castLong(Object obj){
        return castLong(obj,0);
    }

    public static long castLong(Object obj,long defaultValue){
        long longValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(!StringUtil.isEmpty(strValue)){
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                    log.error("解析{}为long类型失败",strValue);
                }
            }
        }
        return longValue;
    }


    public static int castInt(Object obj){
        return castInt(obj,0);
    }

    public static int castInt(Object obj,int defaultValue){
        int intValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(!StringUtil.isEmpty(strValue)){
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                    log.error("解析{}为int类型失败",strValue);
                }
            }
        }
        return intValue;
    }

    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean boolValue = defaultValue;
        if(obj != null){
            boolValue = Boolean.parseBoolean(castString(obj));
        }
        return boolValue;
    }
}
