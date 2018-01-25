package wang.willard.source.cglib;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

public class CglibTest {

    public static void main(String[] args) {
//        sampleTest();

 //       targetObjIntercepterTest();

        targetObjFilterTest();
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

    public static void targetObjIntercepterTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new TargetInterceptor());
        TargetObject obj = (TargetObject) enhancer.create();

        obj.method1("张三");
        obj.method2(3);
        obj.method3(5);
    }


    public static void targetObjFilterTest(){
        Enhancer enhancer =new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        CallbackFilter callbackFilter = new TargetMethodCallbackFilter();

        /**
         * (1)callback1：方法拦截器
         (2)NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
         (3)FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
         */
        Callback noopCb=NoOp.INSTANCE;
        Callback callback1=new TargetInterceptor();
        Callback fixedValue=new TargetResultFixed();
        Callback[] cbarray=new Callback[]{callback1,noopCb,fixedValue};
        //enhancer.setCallback(new TargetInterceptor());
        enhancer.setCallbacks(cbarray);
        enhancer.setCallbackFilter(callbackFilter);
        TargetObject targetObject2=(TargetObject)enhancer.create();
        System.out.println(targetObject2);
        System.out.println(targetObject2.method1("mmm1"));
        System.out.println(targetObject2.method2(100));
        System.out.println(targetObject2.method3(100));
        System.out.println(targetObject2.method3(200));

    }

}
