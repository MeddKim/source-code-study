package com.stuframework.bean.factory.support;

import com.stuframework.bean.factory.BeanFactory;
import com.stuframework.bean.factory.config.AutowireCapableBeanFactory;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    //======================================================================//
    //      构造方法                                                        //
    //======================================================================//
    public AbstractAutowireCapableBeanFactory(){}
    public AbstractAutowireCapableBeanFactory(BeanFactory beanFactory){
        super(beanFactory);
    }

    //======================================================================//
    //      接口AutowireCapableBeanFactory的实现                            //
    //======================================================================//
    @Override
    public Object autowire(Class beanClass, int autowireMode, boolean dependencyCheck) {
        return null;
    }

    @Override
    public void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck) {

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String name) {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String name) {
        return null;
    }
}
