package wang.willard.booknote.container;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;

public class BeanFactoryTest {

    public static void main(String[] args) {
//        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("booknote/beanFactoryTest.xml"));
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("booknote/beanFactoryTest.xml");
//        ApplicationContext context = new ClassPathXmlApplicationContext("booknote/beanFactoryTest.xml");
        MyBeanTest bean = (MyBeanTest) beanFactory.getBean("myTestBean");

        System.out.println(beanFactory.containsBeanDefinition("myTestBean"));
        System.out.println(bean.getTestStr());

//        AnnotationConfigApplicationContext annotationFactory = new AnnotationConfigApplicationContext(MyConfig.class);
//        MyBeanTest beanTest = (MyBeanTest) annotationFactory.getBean("myTestBeanAnno");
//        System.out.println(beanTest.getTestStr());

    }
}
