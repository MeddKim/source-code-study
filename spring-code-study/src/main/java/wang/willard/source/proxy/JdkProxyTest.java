package wang.willard.source.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) {
        //获取动态代理
        ISubject subject = new SubjectImpl();

        //生成代理
        InvocationHandler logHandler = new LogHandler(subject);
        ISubject subjectProxy = (ISubject)Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),logHandler);

        System.out.println(subjectProxy.age(1000));
        System.out.println("--------------");
        System.out.println(subjectProxy.name("Harry Potter"));
    }
}
