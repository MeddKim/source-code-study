package com.stuframework.bean.factory;

public interface BeanFactory {
    Object getBean(String name);
    Object getBean(String name,Class requireType);
    boolean containsBean(String name);
    boolean isSingleton(String name);

    /**
     * 获取别名
     * @param name
     * @return
     */
    String[] getAliases(String name);
}
