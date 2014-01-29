package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.domain.product.ProductItem
import com.orange.ru.mongodb.repositories.BvpnTariffsRepository

@Service("ref_standard_price_payment_2M_monthly_money")
@Repository
class RefStandardPricePayment_2M_Monthly {
  @Autowired BvpnTariffsRepository bvpnTariffsRepository
  def calculate = { ProductItem item ->
    // Выбор базового ежемесячного платежа по городу, виду, типу порта и схеме тарификации данные
    // из справочника "Стандартные тарифы для скорости 2048K"
    item.addMoney("ref_standard_price_payment_2M_monthly_money", bvpnTariffsRepository.getFixValueByProduct(item.product))
  }
}