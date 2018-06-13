package com.stuframework.beans.factory.config;

import com.stuframework.beans.factory.BeanFactory;
import com.stuframework.beans.factory.HierarchicalBeanFactory;

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
