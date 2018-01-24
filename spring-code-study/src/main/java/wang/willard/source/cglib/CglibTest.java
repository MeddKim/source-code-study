package wang.willard.source.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {

    public static void main(String[] args) {
        sampleTest();
    }

    public static void sampleTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("-------打印开始-------");
                Object result = proxy.invokeSuper(obj,args);
                System.out.println("-------打印结束-------");
                return result;
            }
        });

        SampleClass sampleClass = (SampleClass)enhancer.create();
        sampleClass.test();

    }

}
