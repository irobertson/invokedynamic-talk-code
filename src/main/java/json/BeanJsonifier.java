package json;
public class BeanJsonifier  implements Jsonifier<Bean> {
  @Override
  public String marshal(Bean bean) {
    return "{prop1: " + bean.i
      + ", prop2: " + bean.s() + "}";
  }
}
