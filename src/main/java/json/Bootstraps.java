package json;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Bootstraps {
  public static CallSite getField(
    MethodHandles.Lookup lookup, String name, MethodType methodType, Class<?> marshalledClass, String fieldName)
      throws IllegalAccessException, NoSuchFieldException, SecurityException {
    Field field = marshalledClass.getDeclaredField(fieldName);
    field.setAccessible(true);
    MethodHandle methodHandle = lookup.unreflectGetter(field);
    return new ConstantCallSite(methodHandle);
  }

  public static CallSite invokeMethod(
    MethodHandles.Lookup lookup, String name, MethodType methodType, Class<?> marshalledClass, String methodName)
      throws IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException {
    Method method = marshalledClass.getDeclaredMethod(methodName);
    MethodHandle methodHandle = lookup.unreflect(method);
    return new ConstantCallSite(methodHandle);
  }
}
