package com.orange.ru.core.converter;

import com.orange.ru.domain.Port;
import com.orange.ru.domain.product.enums.PortType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import java.util.*;
/**
 * Конвертор.
 * User: Зайнуллин Радик
 * Date: 19.07.13
 */
public final class StringToPortTypeConverter implements GenericConverter {
  private final Class<?> clazz;
  public StringToPortTypeConverter(Class<?> clazz) {
    super();
    this.clazz = clazz;
  }
  @Override
  public Set<ConvertiblePair> getConvertibleTypes() {
    Set<ConvertiblePair> types = new HashSet<ConvertiblePair>();
    types.add(new ConvertiblePair(String.class, this.clazz));
    types.add(new ConvertiblePair(this.clazz, String.class));
    return types;
  }
  @Override
  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    if (String.class.equals(sourceType.getType())){
      return PortType.getKey(Integer.parseInt((String)source));
    } else if (this.clazz.equals(sourceType.getType())) {
      if (source == null) return "";
      else return ((PortType) source).getValue().toString();
    }
    throw new IllegalArgumentException("Cannot convert " + source + " into a suitable type!");
  }
}