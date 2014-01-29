package com.orange.ru.core.converter;

import com.orange.ru.domain.product.enums.LinesType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import java.util.*;

public final class StringToChannelConverter implements GenericConverter {
  private final Class<?> clazz;
  public StringToChannelConverter(Class<?> clazz) {
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
      return LinesType.getKey(Integer.parseInt((String) source));
    } else if (this.clazz.equals(sourceType.getType())) {
      if (source == null) return "";
      else return ((LinesType) source).getValue().toString();
    }
    throw new IllegalArgumentException("Cannot convert " + source + " into a suitable type!");
  }
}