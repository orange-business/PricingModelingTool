package com.orange.ru.domain.product.comparator;

import com.orange.ru.domain.product.wrapper.WrapperMoney;
import java.io.Serializable;
import java.util.Comparator;

public class MoneyComparator implements Serializable, Comparator<WrapperMoney> {
  @Override public int compare(WrapperMoney t1, WrapperMoney t2) {
    return t1.getIdentification().getCode().compareTo(t2.getIdentification().getCode());
  }
}