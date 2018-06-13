package com.stuframework.beans.factory.xml;

import com.stuframework.beans.factory.support.BeanDefinitionRegistry;
import com.stuframework.core.io.Resource;
import org.w3c.dom.Document;
public interface XmlBeanDefinitionParser {
    void registerBeanDefinitions(BeanDefinitionRegistry beanFactory, ClassLoader beanClassLoader,
                                 Document doc, Resource resource);
}
