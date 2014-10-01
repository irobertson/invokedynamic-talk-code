package json.asmexample;

import java.util.*;
import org.objectweb.asm.*;


/**
 * Result of running
 * <pre>
 *   java -classpath asm-all-5.0.3.jar org.objectweb.asm.util.ASMifier target/classes/json/asmexample/Foo.class
 * </pre>
 */
public class FooDump implements Opcodes {

  public static byte[] dump() throws Exception {

    ClassWriter cw = new ClassWriter(0);
    FieldVisitor fv;
    MethodVisitor mv;
    AnnotationVisitor av0;

    cw.visit(52, ACC_PUBLIC + ACC_SUPER, "json/asmexample/Foo", null, "java/lang/Object",
      null);

    cw.visitSource("Foo.java", null);

    {
      mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      mv.visitCode();
      mv.visitVarInsn(ALOAD, 0);
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",
        false);
      mv.visitInsn(RETURN);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
    }
    {
      mv = cw
        .visitMethod(ACC_PUBLIC, "bar", "()Ljava/lang/String;", null, null);
      mv.visitCode();
      mv.visitLdcInsn("hello");
      mv.visitInsn(ARETURN);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
    }
    cw.visitEnd();

    return cw.toByteArray();
  }
}
