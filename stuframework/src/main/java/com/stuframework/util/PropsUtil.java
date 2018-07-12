package com.stuframework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

    private static final Logger log = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载propertis属性文件
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties props = null;
        InputStream is = null;
        try {
            is = ClassUtil.getClassLoader().getResourceAsStream(fileName);
            if(is == null){
                throw new FileNotFoundException(fileName + " file is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (Exception e) {
            log.error("load properties file failure", e);
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                }catch (IOException e){
                    log.error("close input stream failure", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取String类型属性值（默认空字符串）
     * @param props
     * @param key
     * @return
     */
    public static String getString(Properties props,String key){
        return getString(props,key,"");
    }

    /**
     * 获取String类型属性值（可指定默认值       ）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties props,String key,String defaultValue){
        String value = defaultValue;
        if(props.containsKey(key)){
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     * 获取整形属性值（默认为0）
     */
    public static int getInt(Properties props, String key){
        return getInt(props,key,0);
    }
    /**
     * 获取整形属性值（指定默认值）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Properties props, String key, int defaultValue){
        int value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castInt(props.get(key));
        }
        return value;
    }

    /**
     * 获取 boolean 类型属性（默认值为false）
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties props, String key){
        return getBoolean(props,key,false);
    }

    /**
     * 获取 boolean 类型属性（可指定默认值）
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Properties props, String key, boolean defaultValue){
        boolean value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castBoolean(props.get(key));
        }
        return value;
    }
}
