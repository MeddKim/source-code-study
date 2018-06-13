package com.stuframework.beans.factory.config;

import com.stuframework.beans.MutablePropertyValues;

public interface BeanDefinition {

    MutablePropertyValues getPropertyValues();

    ConstructorArgumentValues getConstructorArgumentValues();

    String getResourceDescription();
}
