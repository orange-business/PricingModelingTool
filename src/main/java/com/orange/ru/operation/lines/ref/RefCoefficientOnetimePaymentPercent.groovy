package com.orange.ru.operation.lines.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.service.IdentificationService
import com.orange.ru.domain.product.ProductItem

@Service("ref_coefficient_onetime_payment_percent")
@Repository
class RefCoefficientOnetimePaymentPercent {
  def calculate = { ProductItem item ->
    item.addDouble("ref_coefficient_onetime_payment_percent", 30.0)
  }
}