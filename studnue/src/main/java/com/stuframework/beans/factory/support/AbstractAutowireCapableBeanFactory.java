package com.stuframework.beans.factory.support;

import com.stuframework.beans.factory.BeanFactory;
import com.stuframework.beans.factory.config.AutowireCapableBeanFactory;

import java.util.Map;

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

    //======================================================================//
    //                AbstractBeanFactory中抽象方法的实现                   //
    //======================================================================//
    protected Object createBean(String beanName, RootBeanDefinition mergedBeanDefinition){
        return null;
    }

    protected void destroyBean(String beanName, Object bean){

    }
    //======================================================================//
    //      子类需要实现的抽象方法                                          //
    //======================================================================//

    protected abstract Map findMatchingBeans(Class requiredType);

    protected abstract String[] getDependingBeanNames(String beanName) ;
}
