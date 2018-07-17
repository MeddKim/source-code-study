package com.base.io.self.aio;

public class Test {
    public static void main(String[] args) {
        Person person = new Person();

        if(null != person | person.getName().equals("")){
            System.out.println("呵呵呵");
        }
//        System.out.println(person.getName());
    }
}

class Person{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
