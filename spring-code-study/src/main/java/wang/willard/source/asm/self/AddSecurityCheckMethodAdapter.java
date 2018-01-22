package wang.willard.source.asm.self;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ASM4;

public class AddSecurityCheckMethodAdapter extends MethodVisitor {
    public AddSecurityCheckMethodAdapter(MethodVisitor mv) {
        super(ASM4, mv);
    }

    public void visitCode(){
        visitMethodInsn(Opcodes.INVOKESTATIC, "SecurityChecker",
                "checkSecurity", "()V");
    }
}
