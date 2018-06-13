package com.stuframework.bean.factory;

import java.util.Map;

/**
 * Listable : 列在单子上的
 */
public interface ListableBeanFactory extends BeanFactory {
    int getBeanDefinitionCount();
    String[] getBeanDefinitionNames();
    String[] getBeanDefinitionNames(Class type);
    boolean containsBeanDefinition(String name);
    Map getBeansOfType();
}
