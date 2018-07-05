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
            if(!StringUtils.isBlank(strValue)){
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

}
