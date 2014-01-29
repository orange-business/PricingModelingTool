package com.orange.ru.operation.bvpn.ref

import org.joda.money.BigMoney
import org.springframework.stereotype.*
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.domain.product.ProductItem
import com.orange.ru.mongodb.repositories.BvpnTariffsRepository

@Service("ref_tariff_ubb_all_colors_1MB_money")
@Repository
class RefTariffUbbAllColors_1MB {
  @Autowired BvpnTariffsRepository bvpnTariffsRepository

  def calculate = { ProductItem item ->
    //  стандартная цена за 1 mb траффика, классы сервиса - Data3,Data2,Data1,Voice,Video
    BigMoney data3 = bvpnTariffsRepository.findByTown(item.product.town).getD3Value(item.product.port.coverage)
    item.addMoney("ref_tariff_ubb_data3_1MB_money", data3)
    BigMoney data2 = bvpnTariffsRepository.findByTown(item.product.town).getD2Value(item.product.port.coverage)
    item.addMoney("ref_tariff_ubb_data2_1MB_money", data2)
    BigMoney data1 = bvpnTariffsRepository.findByTown(item.product.town).getD1Value(item.product.port.coverage)
    item.addMoney("ref_tariff_ubb_data1_1MB_money", data1)
    BigMoney voice = bvpnTariffsRepository.findByTown(item.product.town).getVoiceValue(item.product.port.coverage)
    item.addMoney("ref_tariff_ubb_voice_1MB_money", voice)
    BigMoney video = bvpnTariffsRepository.findByTown(item.product.town).getVideoValue(item.product.port.coverage)
    item.addMoney("ref_tariff_ubb_video_1MB_money", video)
  }
}