package wang.willard.source.asm.blog;

import org.objectweb.asm.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.objectweb.asm.Opcodes.*;

public class CustomClassWriter {
    ClassReader reader;
    ClassWriter writer;
    AddFieldAdapter addFieldAdapter;
    AddInterfaceAdapter addInterfaceAdapter;
    PublicizeMethodAdapter pubMethAdapter;
    final static String CLASSNAME = "java.lang.Integer";
    final static String CLONEABLE = "java/lang/Cloneable";

    public CustomClassWriter() {

        try {
            reader = new ClassReader(CLASSNAME);
            writer = new ClassWriter(reader, 0);

        } catch (IOException ex) {
            Logger.getLogger(CustomClassWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CustomClassWriter(byte[] contents) {
        reader = new ClassReader(contents);
        writer = new ClassWriter(reader, 0);
    }

    public byte[] addField() {
        addFieldAdapter = new AddFieldAdapter("aNewBooleanField", ACC_PUBLIC, writer);
        reader.accept(addFieldAdapter, 0);
        return writer.toByteArray();
    }

    public byte[] publicizeMethod() {
        pubMethAdapter = new PublicizeMethodAdapter(writer);
        reader.accept(pubMethAdapter, 0);
        return writer.toByteArray();
    }

    public byte[] addInterface() {
        addInterfaceAdapter = new AddInterfaceAdapter(writer);
        reader.accept(addInterfaceAdapter, 0);
        return writer.toByteArray();
    }

}
