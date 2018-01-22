package wang.willard.source.asm.self;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import wang.willard.source.asm.blog.AddFieldAdapter;
import wang.willard.source.asm.blog.CustomClassWriter;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class Test {
    public static void main(String[] args) throws IOException {
//        byte[] content = addFieldTest();
//        generateClassFile(content,"test.class");
        byte[] content = modifyMethod();
        generateClassFile(content,"test.class");
    }

    public static void generateClassFile(byte[] content,String filename){
        try {
            OutputStream out = new FileOutputStream(filename);
            InputStream is = new ByteArrayInputStream(content);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1){
                out.write(buff,0,len);
            }
            is.close();
            out.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static byte[] addFieldTest(){
        try {
            ClassReader reader = new ClassReader("wang.willard.source.asm.self.Account");
            ClassWriter writer = new ClassWriter(reader, 0);

            AddFieldAdapter addFieldAdapter = new AddFieldAdapter("testFiled",ACC_PUBLIC,writer);

            reader.accept(addFieldAdapter,0);

            return writer.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(CustomClassWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static byte[] modifyMethod(){
        try {
            ClassReader reader = new ClassReader("wang.willard.source.asm.self.Account");
            ClassWriter writer = new ClassWriter(reader, 0);

            ClassVisitor addMethod = new AddSecurityCheckClassAdapter(writer);

//            reader.accept(addMethod,0);
            reader.accept(addMethod, ClassReader.SKIP_DEBUG);

            return writer.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(CustomClassWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
