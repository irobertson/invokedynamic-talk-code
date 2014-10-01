package json;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Pair of bootstrap methods needed for accessing potentially private fields and methods
 */
public class Bootstraps {
  /**
   * InvokeDynamic bootstrap method to create a field-read call site.
   *
   * @param lookup The lookup context, used for getting a MethodHandle
   * @param name the "name" of our "method" - unused
   * @param methodType signature of the method/opcode we should return
   * @param marshalledClass the class containing the field (Bootstrap Method Argument)
   * @param fieldName the name of the field (Bootstrap Method Argument)
   * @return a callsite
   */
  public static CallSite getField(
    MethodHandles.Lookup lookup, String name, MethodType methodType, Class<?> marshalledClass, String fieldName)
      throws IllegalAccessException, NoSuchFieldException, SecurityException {
    Field field = marshalledClass.getDeclaredField(fieldName);
    field.setAccessible(true);
    MethodHandle methodHandle = lookup.unreflectGetter(field);
    return new ConstantCallSite(methodHandle);
  }

  /**
   * InvokeDynamic bootstrap method to create a no-arg method invoke call site.
   *
   * @param lookup The lookup context, used for getting a MethodHandle
   * @param name the "name" of our "method" - unused
   * @param methodType signature of the method/opcode we should return
   * @param marshalledClass the class containing the method (Bootstrap Method Argument)
   * @param methodName the name of the field (Bootstrap Method Argument)
   * @return a callsite
   */
  public static CallSite invokeMethod(
    MethodHandles.Lookup lookup, String name, MethodType methodType, Class<?> marshalledClass, String methodName)
      throws IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException {
    Method method = marshalledClass.getDeclaredMethod(methodName);
    MethodHandle methodHandle = lookup.unreflect(method);
    return new ConstantCallSite(methodHandle);
  }
}
