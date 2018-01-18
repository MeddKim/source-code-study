package wang.willard.source.asm.blog;

import org.objectweb.asm.ClassVisitor;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;
import static wang.willard.source.asm.blog.CustomClassWriter.CLONEABLE;

public class AddInterfaceAdapter extends ClassVisitor {

    public AddInterfaceAdapter(ClassVisitor cv) {
        super(ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        String[] holding = new String[interfaces.length + 1];
        holding[holding.length - 1] = CLONEABLE;
        System.arraycopy(interfaces, 0, holding, 0, interfaces.length);

        cv.visit(V1_5, access, name, signature, superName, holding);
    }

}