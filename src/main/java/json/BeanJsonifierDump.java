package json;

import org.objectweb.asm.*;

public class BeanJsonifierDump implements Opcodes {

  public static byte[] dump() throws Exception {

    ClassWriter cw = new ClassWriter(0);

    cw.visit(52, ACC_PUBLIC + ACC_SUPER, "json/BeanJsonifier1",
      "Ljava/lang/Object;Ljson/Jsonifier<Ljson/Bean;>;", "java/lang/Object",
      new String[] { "json/Jsonifier" });

    cw.visitSource("BeanJsonifier1.java", null);

    generateConstructor(cw);
    generateMarshal(cw);
    generateBridge(cw);
    cw.visitEnd();

    return cw.toByteArray();
  }

  private static void generateBridge(ClassWriter cw) {
    MethodVisitor mv;
    mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "marshal",
      "(Ljava/lang/Object;)Ljava/lang/String;", null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitVarInsn(ALOAD, 1);
    mv.visitTypeInsn(CHECKCAST, "json/Bean");
    mv.visitMethodInsn(INVOKEVIRTUAL, "json/BeanJsonifier1", "marshal",
      "(Ljson/Bean;)Ljava/lang/String;", false);
    mv.visitInsn(ARETURN);
    mv.visitMaxs(2, 2);
    mv.visitEnd();
  }

  private static void generateMarshal(ClassWriter cw) {
    MethodVisitor mv;
    mv = cw.visitMethod(ACC_PUBLIC, "marshal",
      "(Ljson/Bean;)Ljava/lang/String;", null, null);
    mv.visitCode();
    mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
    mv.visitInsn(DUP);
    mv.visitLdcInsn("{prop1: ");
    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>",
      "(Ljava/lang/String;)V", false);
    mv.visitVarInsn(ALOAD, 1);
    mv.visitFieldInsn(GETFIELD, "json/Bean", "i", "I");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
      "(I)Ljava/lang/StringBuilder;", false);
    mv.visitLdcInsn(", prop2: ");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
      "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
    mv.visitVarInsn(ALOAD, 1);
    mv.visitMethodInsn(INVOKEVIRTUAL, "json/Bean", "s",
      "()Ljava/lang/String;", false);
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
      "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
    mv.visitLdcInsn("}");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
      "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString",
      "()Ljava/lang/String;", false);
    mv.visitInsn(ARETURN);
    mv.visitMaxs(3, 2);
    mv.visitEnd();
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
}
