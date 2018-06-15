package com.stuframework.beans.definition;

import com.stuframework.beans.exception.BeanException;

public abstract class AbstractBeanDefinition implements BeanDefinition{
    private MutablePropertyValues propertyValues;

    private String resourceDescription;

    //是否是单例bean（默认true）
    private boolean singleton = true;

    private boolean lazyInit = false;

    protected AbstractBeanDefinition(MutablePropertyValues pvs) {
        this.propertyValues = (pvs != null) ? pvs : new MutablePropertyValues();
    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    @Override
    public ConstructorArgumentValues getConstructorArgumentValues() {
        return null;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    @Override
    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void validate() throws BeanException {
        if (this.lazyInit && !this.singleton) {
            throw new BeanException("只有单例类型Bean须有懒初始化");
        }
    }
}
