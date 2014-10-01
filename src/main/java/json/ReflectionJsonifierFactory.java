package json;

/**
 * Factory for creating {@link Jsonifier}s using {@link ReflectionJsonifier}
 */
public class ReflectionJsonifierFactory {
  public static <T> Jsonifier<T> createReflectionJsonifier(Class<T> clazz) {
    return new ReflectionJsonifier<T>(clazz);
  }
}
