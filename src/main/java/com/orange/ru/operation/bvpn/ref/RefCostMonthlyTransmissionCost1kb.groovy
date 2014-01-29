package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.domain.product.ProductItem
import org.joda.money.BigMoney
import com.orange.ru.mongodb.repositories.BvpnCostsRepository

@Service("ref_cost_monthly_transmission_cost_1kb_money")
@Repository
class RefCostMonthlyTransmissionCost1kb {
  @Autowired BvpnCostsRepository bvpnCostsRepository
  def calculate = { ProductItem item ->
    // Ежемесячный кост: transmission кост (на 1 kbit/сек) ref_cost_monthly_transmission_cost_1kb_money
    BigMoney money = bvpnCostsRepository.getMoneyByTownAndCoverage(item.product.town, item.product.port.coverage)
    item.addMoney("ref_cost_monthly_transmission_cost_1kb_money",money)
  }
}