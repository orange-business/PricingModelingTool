package com.orange.ru.operation.lines.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.service.IdentificationService
import com.orange.ru.domain.product.ProductItem

@Service("ref_standard_required_price_payback_months")
@Repository
class RefStandardRequiredPricePaybackMonths {
  def calculate = { ProductItem item ->     // 12/18/24
    double res = 12
    if (item.getDouble("in_contract_term_months")==24) res = 18
    else if (item.getDouble("in_contract_term_months")==36) res = 24

    item.addDouble("ref_standard_required_price_payback_months", res)
  }
}