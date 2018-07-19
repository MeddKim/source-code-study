package com.base.io.stream;

import java.io.*;

public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("D:"+File.separator+"content.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String str;
        while((str = reader.readLine()) != null){
            System.out.println(str);
        }


        reader.close();
        in.close();
    }
}
