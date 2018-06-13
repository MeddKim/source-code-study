package com.studnue.coretest;

import com.studnue.core.Constants;
import com.studnue.core.io.ClassPathResource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceTest {

    @Test
    public void ClassPathTest() throws IOException {


        //# file:/D:/self/dev/source-code-study/studnue/out/test/classes/com/studnue/coretest/
        System.out.println(ConstantsCase.class.getResource(""));  // 从当前路径开始查找

        //# file:/D:/self/dev/source-code-study/studnue/out/test/classes/
        System.out.println(ConstantsCase.class.getResource("/"));  //从类路径（**/classes 开始查找，即classpath）

        //# file:/D:/self/dev/source-code-study/studnue/out/test/classes/
        System.out.println(ClassPathResource.class.getClassLoader().getResource(""));   //从类路径（**/classes 开始查找，即classpath）

        //# null
        System.out.println(ClassPathResource.class.getClassLoader().getResource("/"));

        //# file:/D:/self/dev/source-code-study/studnue/out/test/classes/
        System.out.println(ClassLoader.getSystemResource(""));  //从类路径（**/classes 开始查找，即classpath）

        //# null
        System.out.println(ClassLoader.getSystemResource("/"));

        //# D:\self\dev\source-code-study\studnue
        System.out.println(System.getProperty("user.dir"));  //项目路径

        //# file:/D:/self/dev/source-code-study/studnue/out/test/classes/
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        //# null
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("/"));

//        byte[] b = new byte[1024];
//        InputStream input = ConstantsCase.class.getResourceAsStream("/read.txt");
//        input.read(b);
//        System.out.println(new String(b,"UTF-8"));
//        input.close();




    }


    @Test
    public void TestResource() throws IOException {
//        ClassPathResource resource = new ClassPathResource("/read.txt");
//        System.out.println(resource.getURL());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("/"));
        System.out.println(ConstantsCase.class.getResource("read.txt"));
        System.out.println(ConstantsCase.class.getResource("/read.txt"));
        System.out.println("-----------------------------------------------------------");
        System.out.println( new ClassPathResource("/read.txt").getURL());
        System.out.println(new ClassPathResource("read.txt").getURL());
        System.out.println(new ClassPathResource("/read.txt",ConstantsCase.class).getURL());
        System.out.println(new ClassPathResource("read.txt",ConstantsCase.class).getURL());

    }
}
