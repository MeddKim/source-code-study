package wang.willard.source.asm.blog;

import java.io.*;

public class ReaderAndWriterTest {

    public static void main(String[] args) throws IOException {
        CustomClassWriter ccw = new CustomClassWriter();
        ccw.publicizeMethod();
        byte[] content = ccw.addField();
        OutputStream out = new FileOutputStream("Integer.class");
        InputStream is = new ByteArrayInputStream(content);
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = is.read(buff)) != -1){
            out.write(buff,0,len);
        }
        is.close();
        out.close();
    }
}
