package wang.willard.source.cglib;

public class TargetObject {

    public String method1(String paramName){
        System.out.println("name："+paramName);
        return paramName;
    }

    public int method2(int count){
        System.out.println("the count of method1："+count);
        return count;
    }

    public int method3(int count){
        System.out.println("the count of method2："+count);
        return count;
    }
}
