package com.orange.ru.operation.lines.ref

import org.springframework.stereotype.*
import com.orange.ru.domain.product.ProductItem

@Service("ref_cost_executive_salary_percent")
@Repository
class RefCostExecutiveSalaryPercent {
  def calculate = { ProductItem item ->
    item.addDouble("ref_cost_executive_salary_percent", 14.0)
  }
}