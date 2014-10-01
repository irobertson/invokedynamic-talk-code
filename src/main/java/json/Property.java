package json;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;

public abstract class Property<E extends AccessibleObject & Member> {
  protected final E element;
  private final String name;

  protected Property(E element) {
    this.element = element;
    element.setAccessible(true);
    this.name = element.getAnnotation(Json.class).value();
  }

  public String getName() {
    return name;
  }

  public abstract Class<?> getPropertyType();

  public abstract Object getPropertyValue(Object instance);

  public abstract boolean isField();

  public abstract String getElementName();
}
