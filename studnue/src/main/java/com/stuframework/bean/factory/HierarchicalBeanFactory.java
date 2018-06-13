package com.stuframework.bean.factory;

/**
 * Hierarchical : 分层的; 按等级划分的
 */
public interface HierarchicalBeanFactory extends BeanFactory{
    BeanFactory getParentBeanFactory();
}
