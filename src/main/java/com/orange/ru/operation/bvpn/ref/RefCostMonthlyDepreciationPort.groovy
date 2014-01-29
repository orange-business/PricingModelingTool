package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import com.orange.ru.domain.product.ProductItem

@Service("ref_cost_monthly_depreciation_port_money")
@Repository
class RefCostMonthlyDepreciationPort {
  def calculate = { ProductItem item ->
    // Ежемесячный кост: амортизация сети доступа (на порт) ref_cost_monthly_depreciation_port_money 76.48 рублей
    item.addMoney("ref_cost_monthly_depreciation_port_money", 76.48)
  }
}