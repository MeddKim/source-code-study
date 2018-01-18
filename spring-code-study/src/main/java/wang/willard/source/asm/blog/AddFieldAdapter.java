package wang.willard.source.asm.blog;



import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Type.BOOLEAN_TYPE;

public class AddFieldAdapter extends ClassVisitor {

    String fieldName;
    int access;
    boolean isFieldPresent;

    public AddFieldAdapter(String fieldName, int access, ClassVisitor cv) {
        super(ASM4, cv);
        this.cv = cv;
        this.access = access;
        this.fieldName = fieldName;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc,
                                   String signature, Object value) {
        if (name.equals(fieldName)) {
            isFieldPresent = true;
        }
        return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!isFieldPresent) {
            FieldVisitor fv = cv.visitField(access, fieldName, BOOLEAN_TYPE.toString(), null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }

}