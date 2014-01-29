package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import com.orange.ru.domain.product.ProductItem
import com.orange.ru.mongodb.reference.bvpntariffs.BusinessVpnTariffs
import org.joda.money.BigMoney
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.mongodb.repositories.BvpnTariffsRepository

@Service("ref_payment_monthly_min_fee_2M_ubb_money")
@Repository
class RefPaymentMonthlyMinFee2M_Ubb {
  @Autowired BvpnTariffsRepository bvpnTariffsRepository

  def calculate = { ProductItem item ->
    BusinessVpnTariffs tariffs = bvpnTariffsRepository.findByTown(item.product.town)
    BigMoney money = tariffs.getMccValue(item.product.port.coverage, item.product.port.type)

    item.addMoney("ref_payment_monthly_min_fee_2M_ubb_money",money)
  }
}