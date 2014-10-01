package json.benchmarks;

import json.Jsonifier;

public class BeanJsonifier  implements Jsonifier<Bean> {
  @Override
  public String marshal(Bean bean) {
    return "{"
      + "prop1: " + bean.i
      + ", prop2: " + bean.s
      + ", prop3: " + bean.getI()
      + ", prop4: " + bean.getS()
      + "}";
  }
}
