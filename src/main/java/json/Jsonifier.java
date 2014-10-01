package json;

public interface Jsonifier<T> {
  String marshal(T instance);
}
