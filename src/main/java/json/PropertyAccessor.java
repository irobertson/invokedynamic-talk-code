package json;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A getter method.
 */
public class PropertyAccessor extends Property<Method> {

  public PropertyAccessor(Method method) {
    super(method);
  }

  @Override
  public Class<?> getPropertyType() {
    return element.getReturnType();
  }

  @Override
  public Object getPropertyValue(Object instance) {
    try {
      return element.invoke(instance);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean isField() {
    return false;
  }

  @Override
  public String getElementName() {
    return element.getName();
  }
}
