package json;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class ClassProperties {
  private final List<Property<?>> properties = new ArrayList<>();

  public ClassProperties(Class<?> clazz) {
    addAnnotatedProperites(clazz.getDeclaredFields(), PropertyField::new);
    addAnnotatedProperites(clazz.getDeclaredMethods(), PropertyAccessor::new);
  }

  private <E extends AccessibleObject & Member> void addAnnotatedProperites(
    E[] declaredProperties, Function<E, Property<E>> constructor) {
    Arrays.stream(declaredProperties)
      .filter((field) -> field.isAnnotationPresent(Json.class))
      .map(constructor)
      .forEach(properties::add);
  }

  public List<Property<?>> getProperties() {
    return properties;
  }
}
