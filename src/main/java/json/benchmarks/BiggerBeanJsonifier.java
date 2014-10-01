package json.benchmarks;

import json.Jsonifier;

public class BiggerBeanJsonifier  implements Jsonifier<BiggerBean> {
  @Override
  public String marshal(BiggerBean bean) {
    return "{"
      + "prop1: " + bean.i
      + ", prop2: " + bean.s
      + ", prop3: " + bean.getI()
      + ", prop4: " + bean.getS()
      + ", prop5: " + bean.j
      + ", prop6: " + bean.t
      + ", prop7: " + bean.getJ()
      + ", prop8: " + bean.getT()
      + "}";
  }
}
