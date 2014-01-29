package com.orange.ru.operation.lines

import com.orange.ru.domain.product.ProductItem

public interface LinesStandardPriceOperation {
  void call(ProductItem item)
}