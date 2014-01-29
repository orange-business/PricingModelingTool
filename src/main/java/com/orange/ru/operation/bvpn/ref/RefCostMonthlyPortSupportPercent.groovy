package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.*
import com.orange.ru.domain.product.ProductItem

@Service("ref_cost_monthly_port_support_percent")
@Repository
class RefCostMonthlyPortSupportPercent {
  def calculate = { ProductItem item ->
    // Ежемесячный кост: затраты на обслуживание сети сторонними подрядчиками, в процентах от стандартной цены за порт
    // ref_cost_monthly_port_support_percent	1.25 процента	CoeffExtNetworkSup
    item.addDouble("ref_cost_monthly_port_support_percent",1.25)
  }
}