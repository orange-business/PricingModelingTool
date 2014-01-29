package com.orange.ru.operation.lines.ref

import org.springframework.stereotype.*
import com.orange.ru.domain.product.ProductItem

@Service("ref_standard_required_price_margin_percent")
@Repository
class RefStandardRequiredPriceMarginPercent {
  def calculate = { ProductItem item ->
    item.addDouble("ref_standard_required_price_margin_percent", 20.0)
  }
}