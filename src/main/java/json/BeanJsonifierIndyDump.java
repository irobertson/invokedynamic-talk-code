package json;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import org.objectweb.asm.*;
import org.objectweb.asm.util.CheckClassAdapter;

/**
 * Conversion of {@link BeanJsonifierDump} to use invokeDynamic.
 */
public class BeanJsonifierIndyDump implements Opcodes {

  public static byte[] dump() throws Exception {

    ClassWriter cw = new ClassWriter(0);

    ClassVisitor cv = new CheckClassAdapter(cw);
    cv.visit(52, ACC_PUBLIC + ACC_SUPER, "json/BeanJsonifier2",
      "Ljava/lang/Object;Ljson/Jsonifier<Ljson/Bean;>;", "java/lang/Object",
      new String[] { "json/Jsonifier" });

    cv.visitSource("BeanJsonifier2.java", null);

    generateConstructor(cv);
    generateMarshal(cv);
    generateBridge(cv);
    cv.visitEnd();

    return cw.toByteArray();
  }

  private static void generateBridge(ClassVisitor cw) {
    MethodVisitor mv;
    mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "marshal",
      "(Ljava/lang/Object;)Ljava/lang/String;", null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitVarInsn(ALOAD, 1);
    mv.visitTypeInsn(CHECKCAST, "json/Bean");
    mv.visitMethodInsn(INVOKEVIRTUAL, "json/BeanJsonifier2", "marshal",
      "(Ljson/Bean;)Ljava/lang/String;", false);
    mv.visitInsn(ARETURN);
    mv.visitMaxs(2, 2);
    mv.visitEnd();
  }

  private static void generateMarshal(ClassVisitor cw) {
    String bootstrapSignature = MethodType.methodType(
      CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class, Class.class, String.class)
      .toMethodDescriptorString();

    MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "marshal", "(Ljson/Bean;)Ljava/lang/String;", null, null);
    mv.visitCode();
    mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
    mv.visitInsn(DUP);
    mv.visitLdcInsn("{prop1: ");
    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>",
      "(Ljava/lang/String;)V", false);

    mv.visitVarInsn(ALOAD, 1);
    //mv.visitFieldInsn(GETFIELD, "json/Bean", "i", "I");
    mv.visitInvokeDynamicInsn(
      "getI",
      MethodType.methodType(int.class, Bean.class).toMethodDescriptorString(),
      new Handle(
        H_INVOKESTATIC,
        "json/Bootstraps",
        "getField",
        bootstrapSignature),
      Type.getType(Bean.class),
      "i");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);

    mv.visitLdcInsn(", prop2: ");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
      "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);

    mv.visitVarInsn(ALOAD, 1);
    // mv.visitMethodInsn(INVOKEVIRTUAL, "json/Bean", "s", "()Ljava/lang/String;", false);
    mv.visitInvokeDynamicInsn(
      "s",
      MethodType.methodType(String.class, Bean.class).toMethodDescriptorString(),
      new Handle(
        H_INVOKESTATIC,
        "json/Bootstraps",
        "invokeMethod",
        bootstrapSignature),
      Type.getType(Bean.class),
      "s");

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

  private static void generateConstructor(ClassVisitor cw) {
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
