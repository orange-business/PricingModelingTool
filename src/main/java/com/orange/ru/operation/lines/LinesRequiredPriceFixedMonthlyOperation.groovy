package com.orange.ru.operation.lines

import com.orange.ru.domain.product.ProductItem

public interface LinesRequiredPriceFixedMonthlyOperation {
  void call(ProductItem item)
}