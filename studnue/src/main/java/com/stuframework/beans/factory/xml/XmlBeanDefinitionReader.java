package com.stuframework.beans.factory.xml;

import com.studnue.beans.factory.support.BeanDefinitionRegistry;
import com.stuframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.xml.sax.EntityResolver;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private boolean validating = true;

    private EntityResolver entityResolver;

    private Class parserClass = DefaultXmlBeanDefinitionParser.class;

    protected XmlBeanDefinitionReader(BeanDefinitionRegistry beanFactory) {
        super(beanFactory);
    }

}
