package json;

public class JsonifierIndyFactory {

  @SuppressWarnings("unchecked")
  public static <T> Jsonifier<T> createReflectionMarshaller(Class<T> beanClass) {
    IndyJsonifierBytecodeGenerator generator = new IndyJsonifierBytecodeGenerator(beanClass);
    try {
      return (Jsonifier<T>) new ByteClassLoader(beanClass.getClassLoader())
        .loadClass(generator.getMarshallerClassName(), generator.generateBytecode())
        .newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

}
