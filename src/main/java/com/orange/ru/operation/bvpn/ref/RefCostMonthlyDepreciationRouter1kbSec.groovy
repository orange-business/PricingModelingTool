package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import com.orange.ru.domain.product.ProductItem

@Service("ref_cost_monthly_depreciation_router_1kb_sec_money")
@Repository
class RefCostMonthlyDepreciationRouter1kbSec {
  def calculate = { ProductItem item ->
    // Ежемесячный кост: амортизация PE роутера (на 1 kbit/сек) * 1000 - ref_cost_monthly_depreciation_router_1kb_sec_money
    item.addMoney("ref_cost_monthly_depreciation_router_1kb_sec_money", 0.029)
  }
}
