package json;

public class ReflectionJsonifier<T> implements Jsonifier<T> {
  private final ClassProperties classProperties;

  public ReflectionJsonifier(Class<T> clazz) {
    classProperties = new ClassProperties(clazz);
  }

  @Override
  public String marshal(T instance) {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    boolean seenProperty = false;
    for (Property<?> property: classProperties.getProperties()) {
      if (seenProperty) {
        builder.append(", ");
      }
      else {
        seenProperty = true;
      }
      marshallProperty(instance, builder, property);
    }
    builder.append("}");
    return builder.toString();
  }

  private void marshallProperty(T instance, StringBuilder builder,
    Property<?> property) {
    builder.append(property.getName()).append(": ");
    builder.append(property.getPropertyValue(instance));
  }

}
