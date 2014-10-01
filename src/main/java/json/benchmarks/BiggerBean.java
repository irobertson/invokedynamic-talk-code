package json.benchmarks;

import json.Json;

public class BiggerBean {
  public BiggerBean(String s, String s1, String t, String t1) {
    i = 4;
    i1 = 7;
    j = 22;
    j1 = 9;
    this.s = s;
    this.s1 = s1;
    this.t = t;
    this.t1 = t1;
  }

  @Json("prop1")
  public int i;

  @Json("prop2")
  public String s;

  public int i1;
  @Json("prop3")
  public int getI() { return i1; }

  public String s1;
  @Json("prop4")
  public String getS() { return s1; }

  @Json("prop5")
  public int j;

  @Json("prop6")
  public String t;

  public int j1;
  @Json("prop7")
  public int getJ() { return j1; }

  public String t1;
  @Json("prop8")
  public String getT() { return t1; }
}
