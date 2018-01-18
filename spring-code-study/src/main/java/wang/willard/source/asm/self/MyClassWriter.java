package wang.willard.source.asm.self;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import wang.willard.source.asm.blog.AddFieldAdapter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

public class MyClassWriter {
    private ClassWriter writer;
    private ClassReader reader;

    public MyClassWriter(String className){
        try {
            this.reader = new ClassReader(className);
            this.writer =new ClassWriter(reader,0);
        } catch (IOException ex) {
            Logger.getLogger(MyClassWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] addField(ClassVisitor classVisitor){
//        addFieldAdapter = new AddFieldAdapter("aNewBooleanField", ACC_PUBLIC, writer);
        reader.accept(classVisitor, 0);
        return writer.toByteArray();
    }
}
