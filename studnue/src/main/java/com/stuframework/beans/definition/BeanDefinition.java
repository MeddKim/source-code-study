package com.stuframework.beans.definition;


public interface BeanDefinition {
    /**
     * 定义的bean的属性集合
     */
    MutablePropertyValues getPropertyValues();

    /**
     * bean的构造函数参数合集
     */
    ConstructorArgumentValues getConstructorArgumentValues();

    /**
     * 返回bean definition的来源，用于在报错的是否展示context
     */
    String getResourceDescription();
}
