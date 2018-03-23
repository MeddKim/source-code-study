package wang.willard.booknote.container;

import org.springframework.context.annotation.Bean;

public class MyConfig {

    @Bean(name = "myTestBeanAnno")
    private MyBeanTest myBeanTest(){
        MyBeanTest beanTest = new MyBeanTest();
        beanTest.setTestStr("你好");
        return beanTest;
    }
}
