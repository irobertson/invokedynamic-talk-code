package json;
public class Bean {
  @Json("prop1")
  public int i;

  public String s;
  @Json("prop2")
  public String s() { return s; }
}
