package com.stuframework.beans.factory.config;

import com.stuframework.beans.MutablePropertyValues;

/**
 * 该接口保存从配置文件中读取出来的bean信息
 *   MutablePropertyValues保存了定义的配置信息
 *   ConstructorArgumentValues保存构造函数参数信息
 */
public interface BeanDefinition {

    MutablePropertyValues getPropertyValues();

    ConstructorArgumentValues getConstructorArgumentValues();

    //返回该bean来源于那个配置
    String getResourceDescription();
}
