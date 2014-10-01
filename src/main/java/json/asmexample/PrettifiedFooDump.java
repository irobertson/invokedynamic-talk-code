package json.asmexample;

import org.objectweb.asm.*;

/**
 * A modestly refactored version of {@link FooDump}
 */
public class PrettifiedFooDump implements Opcodes {

  public static byte[] dump() throws Exception {

    ClassWriter cw = new ClassWriter(0);

    cw.visit(52, ACC_PUBLIC + ACC_SUPER, "json/Foo", null, "java/lang/Object",
      null);

    cw.visitSource("Foo.java", null);

    generateConstructor(cw);
    generateBar(cw);
    cw.visitEnd();

    return cw.toByteArray();
  }

  private static void generateConstructor(ClassWriter cw) {
    MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",
      false);
    mv.visitInsn(RETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
  }

  private static void generateBar(ClassWriter cw) {
    MethodVisitor mv = cw
      .visitMethod(ACC_PUBLIC, "bar", "()Ljava/lang/String;", null, null);
    mv.visitCode();
    mv.visitLdcInsn("hello");
    mv.visitInsn(ARETURN);
    mv.visitMaxs(1, 1);
    mv.visitEnd();
  }
}
