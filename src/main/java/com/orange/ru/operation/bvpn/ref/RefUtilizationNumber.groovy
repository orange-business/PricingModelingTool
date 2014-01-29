package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import com.orange.ru.domain.product.ProductItem

@Service("ref_utilization_number")
@Repository
class RefUtilizationNumber {
  def calculate = { ProductItem item ->
    // Коэффициент утилизации (Util) Коэффициент утилизации. ref_utilization_number	0.55
    item.addDouble("ref_utilization_number", 0.55)
  }
}