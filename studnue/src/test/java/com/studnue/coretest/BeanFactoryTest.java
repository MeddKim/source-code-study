package com.studnue.coretest;

import com.studnue.beans.factory.xml.XmlBeanFactory;
import com.studnue.core.io.ClassPathResource;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void xmlBeanFactoryTest(){
        ClassPathResource resource = new ClassPathResource("bean.xml");
        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
        beanFactory.getBean("person");
    }
}
