package com.orange.ru.operation.bvpn.ref

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.domain.product.ProductItem
import com.orange.ru.mongodb.repositories.CoefficientRepository

@Service("ref_coeff_tariff_calculation_for_speed_number")
@Repository
class RefCoeffTariffCalculationForSpeed {
  @Autowired CoefficientRepository coefficientRepository

  def calculate = { ProductItem item ->
    // Получить коэффициент расчета ежемесячного платежа по городу и скорости порта
    // из справочника "Коэффициенты пересчета ежемесячных тарифов"
    Double koeff = coefficientRepository.findBySpeedAndTown(item.product.port.speed, item.product.town)
    item.addDouble("ref_coeff_tariff_calculation_for_speed_number", koeff)
  }
}
