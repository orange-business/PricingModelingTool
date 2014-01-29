package com.orange.ru.domain.product.comparator;

import com.orange.ru.domain.product.wrapper.WrapperNumeric;
import java.io.Serializable;
import java.util.Comparator;

public class NumericComparator implements Serializable, Comparator<WrapperNumeric> {
  @Override public int compare(WrapperNumeric t1, WrapperNumeric t2) {
    return t1.getIdentification().getCode().compareTo(t2.getIdentification().getCode());
  }
}