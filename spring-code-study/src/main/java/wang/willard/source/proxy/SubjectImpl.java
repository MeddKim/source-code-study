package wang.willard.source.proxy;

public class SubjectImpl implements ISubject{

    @Override
    public String name(String name){
        System.out.println("print name");
        return "name";
    }

    @Override
    public int age(int age){
        System.out.println("print age");
        return age;
    }
}
