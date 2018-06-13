package com.stuframework.beans.factory.config;

import com.studnue.beans.factory.ListableBeanFactory;
import com.studnue.beans.factory.config.AutowireCapableBeanFactory;
import com.studnue.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {

    void preInstantiateSingletons();

}
