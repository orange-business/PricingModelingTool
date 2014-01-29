package com.orange.ru.operation.ref

import com.orange.ru.domain.product.*
import org.springframework.stereotype.*

@Service("ref_risk_percent")
@Repository
class RefRiskPercent {
  def calculate = { ProductItem item ->
    item.addDouble("ref_risk_percent", 4.0)
  }
}