package com.stuframework.beans.factory;

import com.stuframework.beans.definition.BeanDefinition;

public interface BeanFactory {

    /**
     * 将由bean配置生成的beanDefinition注册到工厂中
     * @param name  bean名称，也就是bean的唯一描述符（在spring对应了ID）
     * @param beanDefinition
     */
    void registerBeanDefinition(String name,BeanDefinition beanDefinition);

    /**
     * 获取Bean , Bean名称
     * @param name
     * @return
     */
    Object getBean(String name);
}
