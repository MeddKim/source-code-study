package wang.willard.booknote.container;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryTest {

    public static void main(String[] args) {
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("booknote/beanFactoryTest.xml"));
//        BeanFactory beanFactory1 = new ClassPathXmlApplicationContext("booknote/beanFactoryTest.xml");
        MyBeanTest bean = (MyBeanTest) beanFactory.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }
}
