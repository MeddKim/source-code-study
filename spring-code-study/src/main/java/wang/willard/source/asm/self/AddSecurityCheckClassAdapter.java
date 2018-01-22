package wang.willard.source.asm.self;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM4;

public class AddSecurityCheckClassAdapter extends ClassVisitor{

    public AddSecurityCheckClassAdapter(ClassVisitor cv) {
        super(ASM4, cv);
        this.cv = cv;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions){
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);

        MethodVisitor wrappedMv = mv;
        if(mv != null){
            if(name.equals("operation")){
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            }
        }
        return wrappedMv;
    }
}
