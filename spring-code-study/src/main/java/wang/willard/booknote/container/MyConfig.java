package wang.willard.booknote.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean(name = "myTestBeanAnno")
    MyBeanTest myBeanTest(){
        MyBeanTest beanTest = new MyBeanTest();
        beanTest.setTestStr("你好");
        return beanTest;
    }
}
