package json;

/**
 * Simple class to demonstrate our various Jsonifier implementations.
 */
public class JsonTest {
  @Json("foo")
  private int i = 4;

  @Json("bar")
  public int x() { return 5; }

  @Json("fu")
  public String j = "jay";

  @Json("bear")
  public String y() { return "boofa"; }

  public static void main(String[] args) throws Exception {
    new JsonTest().run( );

  }

  private void run() throws Exception {
    new ReflectionJsonifierFactory();
    Jsonifier<Bean> jsonifier = ReflectionJsonifierFactory.createReflectionJsonifier(Bean.class);
    Bean bean = new Bean();
    bean.i=3;
    bean.s = "hello";
    System.out.println(jsonifier.marshal(bean));
    System.out.println(new BeanJsonifier().marshal(bean));
    System.out.println(loadJsonifier("json.BeanJsonifier1", BeanJsonifierDump.dump()).marshal(bean));
    System.out.println(loadJsonifier("json.BeanJsonifier2", BeanJsonifierIndyDump.dump()).marshal(bean));
    IndyJsonifierBytecodeGenerator generator = new IndyJsonifierBytecodeGenerator(Bean.class);
    System.out.println(loadJsonifier(generator.getMarshallerClassName(), generator.generateBytecode()).marshal(bean));
    System.out.println();
  }
  private ByteClassLoader byteClassLoader = new ByteClassLoader(Bean.class.getClassLoader());

  @SuppressWarnings("unchecked")
  private Jsonifier<Bean> loadJsonifier(String name, byte[] bytecode) throws Exception {
    return (Jsonifier<Bean>) byteClassLoader.loadClass(name, bytecode).newInstance();
  }
}
