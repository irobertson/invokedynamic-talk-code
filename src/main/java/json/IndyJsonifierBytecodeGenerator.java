package json;

import static org.objectweb.asm.Opcodes.*;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.atomic.AtomicInteger;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.CheckClassAdapter;

/**
 * Bytecode generator for a {@link Jsonifier} that uses invokeDynamic to access fields and methods.
 */
public class IndyJsonifierBytecodeGenerator {
  private final static AtomicInteger counter = new AtomicInteger();
  private final Class<?> jsonClass;
  private final String jsonifierClassName;
  private final String jsonifierClassPath;
  private final String jsonPath;

  public IndyJsonifierBytecodeGenerator(Class<?> jsonClass) {
    this.jsonClass = jsonClass;
    jsonifierClassName = getClass().getName() + "$Marshaller$" + counter.incrementAndGet();
    jsonifierClassPath = jsonifierClassName.replace('.', '/');
    jsonPath = jsonClass.getName().replace('.', '/');
  }

  public String getMarshallerClassName() {
    return jsonifierClassName;
  }

  private static final String BOOTSTRAP_SIGNATURE = MethodType.methodType(
    CallSite.class, MethodHandles.Lookup.class, String.class, MethodType.class, Class.class, String.class)
    .toMethodDescriptorString();
  private final static String BASE_CLASS_INTERNAL_NAME = Bootstraps.class.getName().replace('.', '/');

  public byte[] generateBytecode() {

    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
    ClassVisitor cv = new CheckClassAdapter(cw, true);

    cv.visit(
      52,
      ACC_PUBLIC,
      jsonifierClassPath,
      "Ljava/lang/Object;Ljson/Jsonifier<L" + jsonPath+ ";>;",
      BASE_CLASS_INTERNAL_NAME,
      new String[] { "json/Jsonifier" });

    cv.visitSource(getClass().getName().replace('.', '/') + ".java", null);

    generateConstructor(cv);
    generateMarshal(cv);
    generateMarshalBridge(cv);
    cv.visitEnd();

    return cw.toByteArray();
  }

  private void generateMarshalBridge(ClassVisitor cw) {
    MethodVisitor mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC,
      "marshal",
      "(Ljava/lang/Object;)Ljava/lang/String;", null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitVarInsn(ALOAD, 1);
    mv.visitTypeInsn(CHECKCAST, jsonPath);
    mv.visitMethodInsn(INVOKEVIRTUAL,
      jsonifierClassPath, "marshal",
      "(L" + jsonPath + ";)Ljava/lang/String;", false);
    mv.visitInsn(ARETURN);
    mv.visitMaxs(2, 2);
    mv.visitEnd();
  }

  private void generateMarshal(ClassVisitor cw) {
    MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "marshal",
      "(L" + jsonPath + ";)Ljava/lang/String;", null, null);
    mv.visitCode();
    mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
    mv.visitInsn(DUP);
    mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>",
      "()V", false);
    appendString(mv, "{");
    boolean seenProperty = false;
    ClassProperties classProperties = new ClassProperties(jsonClass);
    for (Property<?> property: classProperties.getProperties()) {
      if (seenProperty) {
        appendString(mv, ", ");
      }
      else {
        seenProperty = true;
      }
      appendString(mv, property.getName());
      appendString(mv, ": ");

      mv.visitVarInsn(ALOAD, 1);
      invokeDynamic(
        mv,
        property.getPropertyType(),
        "access_" + property.getName(), property.isField() ? "getField" : "invokeMethod",
        property.getElementName());
      appendToBuffer(mv, property.getPropertyType());
    }

    appendString(mv, "}");
    mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
    mv.visitInsn(ARETURN);
    mv.visitMaxs(2, 2);
    mv.visitEnd();
  }

  private void invokeDynamic(MethodVisitor mv, Class<?> returnType, String methodName,
    String bootstrapMethod, String elementName) {
    mv.visitInvokeDynamicInsn(
      methodName,
      MethodType.methodType(returnType, jsonClass).toMethodDescriptorString(),
      new Handle(
        H_INVOKESTATIC,
        BASE_CLASS_INTERNAL_NAME,
        bootstrapMethod,
        BOOTSTRAP_SIGNATURE),
      Type.getType(jsonClass),
      elementName);
  }

  private static void appendString(MethodVisitor mv, String value) {
    mv.visitLdcInsn(value);
    appendToBuffer(mv, String.class);
  }

  private static void appendToBuffer(MethodVisitor mv, Class<?> appendedType) {
    mv.visitMethodInsn(
      INVOKEVIRTUAL,
      "java/lang/StringBuilder",
      "append",
      MethodType.methodType(StringBuilder.class, appendedType).toMethodDescriptorString(),
      false);
  }

  private void generateConstructor(ClassVisitor cv) {
    MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
    mv.visitCode();
    mv.visitVarInsn(ALOAD, 0);
    mv.visitMethodInsn(INVOKESPECIAL, BASE_CLASS_INTERNAL_NAME, "<init>", "()V",
      false);
    mv.visitInsn(RETURN);
    mv.visitMaxs(1,1);
    mv.visitEnd();
  }

}
