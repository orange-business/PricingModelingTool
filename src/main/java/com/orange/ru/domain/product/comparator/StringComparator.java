package com.orange.ru.domain.product.comparator;

import com.orange.ru.domain.product.wrapper.WrapperString;
import java.io.Serializable;
import java.util.Comparator;

public class StringComparator implements Serializable, Comparator<WrapperString> {
  @Override public int compare(WrapperString t1, WrapperString t2) {
    return t1.getIdentification().getCode().compareTo(t2.getIdentification().getCode());
  }
}