package json;

/**
 * Simple hand-coded {@link Jsonifier} for {@link Bean}
 */
public class BeanJsonifier  implements Jsonifier<Bean> {
  @Override
  public String marshal(Bean bean) {
    return "{prop1: " + bean.i
      + ", prop2: " + bean.s() + "}";
  }
}
