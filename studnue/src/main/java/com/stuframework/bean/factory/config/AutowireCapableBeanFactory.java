package com.stuframework.bean.factory.config;

public interface AutowireCapableBeanFactory {

    int AUTOWIRE_BY_NAME = 1;

    int AUTOWIRE_BY_TYPE = 2;

    int AUTOWIRE_CONSTRUCTOR = 3;

    int AUTOWIRE_AUTODETECT = 4;

    Object autowire(Class beanClass, int autowireMode, boolean dependencyCheck);

    void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck);

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String name);

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String name);

}
