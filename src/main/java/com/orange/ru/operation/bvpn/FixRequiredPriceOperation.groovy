package com.orange.ru.operation.bvpn

import com.orange.ru.domain.product.ProductItem

public interface FixRequiredPriceOperation {
  void call(ProductItem item)
}