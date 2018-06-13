package com.stuframework.beans.factory.support;

import com.stuframework.beans.MutablePropertyValues;
import com.stuframework.beans.factory.config.BeanDefinition;
import com.stuframework.beans.factory.config.ConstructorArgumentValues;

public abstract class AbstractBeanDefinition implements BeanDefinition {

    private MutablePropertyValues propertyValues;

    private String resourceDescription;

    private boolean singleton = true;

    private boolean lazyInit = false;


    protected AbstractBeanDefinition(MutablePropertyValues pvs) {
        this.propertyValues = (pvs != null) ? pvs : new MutablePropertyValues();
    }

    public MutablePropertyValues getPropertyValues() {
        return propertyValues;
    }

    public ConstructorArgumentValues getConstructorArgumentValues() {
        return null;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

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

    public void validate() {
        if (this.lazyInit && !this.singleton) {
//            throw new BeanDefinitionValidationException("Lazy initialization is just applicable to singleton beans");
        }
    }
}
