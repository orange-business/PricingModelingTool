package com.orange.ru.operation.lines

import com.orange.ru.domain.product.ProductItem

public interface LinesRequiredPriceFixedOnetimeOperation {
  void call(ProductItem item)
}