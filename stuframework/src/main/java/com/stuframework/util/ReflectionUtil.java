package com.stuframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class ReflectionUtil {

    private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     */
    public static Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            log.error("new instance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 设置成员变量的值
     */
    public static void setField(Object obj, Field field, Object value){

        try {
            field.setAccessible(true);
            field.set(obj,value);
        } catch (IllegalAccessException e) {
            log.error("set field failure",e);
            throw new RuntimeException(e);
        }

    }
}
