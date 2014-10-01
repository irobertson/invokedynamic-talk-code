package json;
/**
 * A simple classloader to facilitate creating classes from raw bytecode.
 */
public final class ByteClassLoader extends ClassLoader {
  public ByteClassLoader(ClassLoader parent) {
    super(parent);
  }

  public Class<?> loadClass(String name, byte[] classBytes) {
    return defineClass(name, classBytes, 0, classBytes.length);
  }
}
