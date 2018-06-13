package com.stuframework.bean.factory.config;

import com.stuframework.bean.factory.BeanFactory;
import com.stuframework.bean.factory.HierarchicalBeanFactory;

import java.beans.PropertyEditor;

/**
 * Configurable : 解构的，可配置的
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory{

    void setParentBeanFactory(BeanFactory parentBeanFactory);

    void registerCustomEditor(Class requiredType, PropertyEditor propertyEditor);

    void ignoreDependencyType(Class type);

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    BeanDefinition getBeanDefinition(String beanName);

    void registerAlias(String beanName, String alias);

    void registerSingleton(String beanName, Object singletonObject);

    void destroySingletons();
}
