package json.benchmarks;

import json.Json;

public class Bean {
  public Bean(String s, String s1) {
    this.s = s;
    this.s1 = s1;
  }

  @Json("prop1")
  public int i = 3;

  @Json("prop2")
  public String s;

  public int i1 = 7;
  @Json("prop3")
  public int getI() { return i1; }

  public String s1;
  @Json("prop3")
  public String getS() { return s1; }
}
