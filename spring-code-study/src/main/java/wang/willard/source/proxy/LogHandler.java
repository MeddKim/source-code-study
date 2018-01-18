package wang.willard.source.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandler implements InvocationHandler {

    private Object target;

    LogHandler(){
        super();
    }

    LogHandler(Object target){
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("method " + method.getName() +" start");
        Object result = method.invoke(target,args);
        System.out.println("method " + method.getName() +" end");
        return result;
    }

}
