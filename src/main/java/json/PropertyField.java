package json;

import java.lang.reflect.Field;

/**
 * An instance field.
 */
public class PropertyField extends Property<Field> {
  public PropertyField(Field propertyField) {
    super(propertyField);
  }

  @Override
  public Class<?> getPropertyType() {
    return element.getType();
  }

  @Override
  public Object getPropertyValue(Object instance) {
    try {
      return element.get(instance);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean isField() {
    return true;
  }

  @Override
  public String getElementName() {
    return element.getName();
  }
}
