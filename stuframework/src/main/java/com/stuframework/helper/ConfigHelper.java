package com.stuframework.helper;

import com.stuframework.ConfigConstant;
import com.stuframework.util.PropsUtil;

import java.util.Properties;

public final class ConfigHelper {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);


    /**
     * 获取JDBC 驱动
     * @return
     */
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取 JDBC URL
     */
    public static String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
    }

    /**
     * 获取 JDBC 用户名
     */
    public static String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URSERNAME);
    }

    /**
     * 获取 应用基础包名称
     */
    public static String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用 JSP 路径
     */
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH);
    }

    /**
     * 获取静态资源路径
     */
    public static String getAppAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH);
    }

}
