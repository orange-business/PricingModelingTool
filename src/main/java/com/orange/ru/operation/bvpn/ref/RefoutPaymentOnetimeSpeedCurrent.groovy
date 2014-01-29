package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.domain.product.ProductItem
import com.orange.ru.mongodb.repositories.InstallationRepository

@Service("refout_payment_onetime_speed_current_money")
@Repository
class RefoutPaymentOnetimeSpeedCurrent {
  @Autowired InstallationRepository installationRepository
  def calculate = { ProductItem item ->
    // Выбор разового платежа по типу запроса, скорости порта и сроку контракта из справочника "Базовые ежемесячные тарифы IP VPN"
    item.addMoney("refout_payment_onetime_speed_current_money", installationRepository.getMoneyByItem(item))
  }
}