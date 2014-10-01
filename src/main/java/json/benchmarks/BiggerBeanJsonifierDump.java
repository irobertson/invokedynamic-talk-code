package json.benchmarks;

import json.ByteClassLoader;
import json.Jsonifier;

import org.objectweb.asm.*;

public class BiggerBeanJsonifierDump implements Opcodes {

  @SuppressWarnings("unchecked")
  public static Jsonifier<BiggerBean> makeJsonifier() {
    try {
      return (Jsonifier<BiggerBean>) new ByteClassLoader(BiggerBean.class.getClassLoader())
        .loadClass("json.benchmarks.BiggerBeanJsonifier", dump())
        .newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] dump() throws Exception {

    ClassWriter cw = new ClassWriter(0);
    FieldVisitor fv;
    MethodVisitor mv;
    AnnotationVisitor av0;

    cw.visit(52, ACC_PUBLIC + ACC_SUPER, "json/benchmarks/BiggerBeanJsonifier",
      "Ljava/lang/Object;Ljson/Jsonifier<Ljson/benchmarks/BiggerBean;>;",
      "java/lang/Object", new String[] { "json/Jsonifier" });

    cw.visitSource("BiggerBeanJsonifier.java", null);

    {
      mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
      mv.visitCode();
      Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V",
        false);
      mv.visitInsn(RETURN);
      Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitLocalVariable("this", "Ljson/benchmarks/BiggerBeanJsonifier;",
        null, l0, l1, 0);
      mv.visitMaxs(1, 1);
      mv.visitEnd();
    }
    {
      mv = cw.visitMethod(ACC_PUBLIC, "marshal",
        "(Ljson/benchmarks/BiggerBean;)Ljava/lang/String;", null, null);
      mv.visitCode();
      Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
      mv.visitInsn(DUP);
      mv.visitLdcInsn("{prop1: ");
      mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>",
        "(Ljava/lang/String;)V", false);
      Label l1 = new Label();
      mv.visitLabel(l1);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitFieldInsn(GETFIELD, "json/benchmarks/BiggerBean", "i", "I");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(I)Ljava/lang/StringBuilder;", false);
      Label l2 = new Label();
      mv.visitLabel(l2);
      mv.visitLdcInsn(", prop2: ");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitFieldInsn(GETFIELD, "json/benchmarks/BiggerBean", "s",
        "Ljava/lang/String;");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      Label l3 = new Label();
      mv.visitLabel(l3);
      mv.visitLdcInsn(", prop3: ");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitMethodInsn(INVOKEVIRTUAL, "json/benchmarks/BiggerBean", "getI",
        "()I", false);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(I)Ljava/lang/StringBuilder;", false);
      Label l4 = new Label();
      mv.visitLabel(l4);
      mv.visitLdcInsn(", prop4: ");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitMethodInsn(INVOKEVIRTUAL, "json/benchmarks/BiggerBean", "getS",
        "()Ljava/lang/String;", false);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      Label l5 = new Label();
      mv.visitLabel(l5);
      mv.visitLdcInsn(", prop5: ");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitFieldInsn(GETFIELD, "json/benchmarks/BiggerBean", "j", "I");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(I)Ljava/lang/StringBuilder;", false);
      Label l6 = new Label();
      mv.visitLabel(l6);
      mv.visitLdcInsn(", prop6: ");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitFieldInsn(GETFIELD, "json/benchmarks/BiggerBean", "t",
        "Ljava/lang/String;");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      Label l7 = new Label();
      mv.visitLabel(l7);
      mv.visitLdcInsn(", prop7: ");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitMethodInsn(INVOKEVIRTUAL, "json/benchmarks/BiggerBean", "getJ",
        "()I", false);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(I)Ljava/lang/StringBuilder;", false);
      Label l8 = new Label();
      mv.visitLabel(l8);
      mv.visitLdcInsn(", prop8: ");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitMethodInsn(INVOKEVIRTUAL, "json/benchmarks/BiggerBean", "getT",
        "()Ljava/lang/String;", false);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      Label l9 = new Label();
      mv.visitLabel(l9);
      mv.visitLdcInsn("}");
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
        "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
      Label l10 = new Label();
      mv.visitLabel(l10);
      mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString",
        "()Ljava/lang/String;", false);
      mv.visitInsn(ARETURN);
      Label l11 = new Label();
      mv.visitLabel(l11);
      mv.visitLocalVariable("this", "Ljson/benchmarks/BiggerBeanJsonifier;",
        null, l0, l11, 0);
      mv.visitLocalVariable("bean", "Ljson/benchmarks/BiggerBean;", null, l0,
        l11, 1);
      mv.visitMaxs(3, 2);
      mv.visitEnd();
    }
    {
      mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "marshal",
        "(Ljava/lang/Object;)Ljava/lang/String;", null, null);
      mv.visitCode();
      Label l0 = new Label();
      mv.visitLabel(l0);
      mv.visitVarInsn(ALOAD, 0);
      mv.visitVarInsn(ALOAD, 1);
      mv.visitTypeInsn(CHECKCAST, "json/benchmarks/BiggerBean");
      mv.visitMethodInsn(INVOKEVIRTUAL, "json/benchmarks/BiggerBeanJsonifier",
        "marshal", "(Ljson/benchmarks/BiggerBean;)Ljava/lang/String;", false);
      mv.visitInsn(ARETURN);
      mv.visitMaxs(2, 2);
      mv.visitEnd();
    }
    cw.visitEnd();

    return cw.toByteArray();
  }
}
