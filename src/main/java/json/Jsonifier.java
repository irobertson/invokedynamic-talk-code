package json;

/**
 * A marshaler that creates a JSON representation of an instance of {@code T}.
 *
 * @param <T> the type to marshal
 */
public interface Jsonifier<T> {
  /**
   * Create a JSON representation of {@code instance}
   * @param instance the instance to marshal
   * @return the JSON representation
   */
  String marshal(T instance);
}
