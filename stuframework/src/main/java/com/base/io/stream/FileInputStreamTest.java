package com.base.io.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream("D:"+ File.separator+"content.txt");
        byte[] buffer = new byte[8];
        //读取，从0字节开始，读取1024个字节
        int size = fin.read(buffer,0,buffer.length);
        String content = new String(buffer);

        System.out.println(content);
        System.out.println(size);

        fin.close();
    }
}
