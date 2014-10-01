package json;

public class ReflectionJsonifierFactory {
  public static <T> Jsonifier<T> createReflectionJsonifier(Class<T> clazz) {
    return new ReflectionJsonifier<T>(clazz);
  }
}
