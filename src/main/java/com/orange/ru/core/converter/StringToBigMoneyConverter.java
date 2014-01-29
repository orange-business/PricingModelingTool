package com.orange.ru.core.converter;

import org.apache.commons.lang3.StringUtils;
import org.joda.money.BigMoney;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import java.util.*;

public final class StringToBigMoneyConverter implements GenericConverter {
  private final Class<?> clazz;
  public StringToBigMoneyConverter(Class<?> clazz) {
    super();
    this.clazz = clazz;
  }

  public Set<ConvertiblePair> getConvertibleTypes() {
    Set<ConvertiblePair> types = new HashSet<ConvertiblePair>();
    types.add(new ConvertiblePair(String.class, this.clazz));
    types.add(new ConvertiblePair(this.clazz, String.class));
    return types;
  }

  public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
    if (String.class.equals(sourceType.getType())) {
      if (StringUtils.isBlank((String) source)) return null;
      // в метод передается код услуги
      return BigMoney.parse("RUR " + (String) source);
    } else if (this.clazz.equals(sourceType.getType())) {
        if (source == null) return "";
        else return ((BigMoney) source).getAmount().toString();
    }
    throw new IllegalArgumentException("Cannot convert " + source + " into a suitable type!");
  }
}