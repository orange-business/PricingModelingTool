package com.orange.ru.operation.bvpn

import com.orange.ru.domain.product.ProductItem

public interface FixStandardPriceOperation {
  void call(ProductItem item)
}