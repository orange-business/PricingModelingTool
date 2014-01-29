package com.orange.ru.domain.product.renew

import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.operation.ref.FillIdentification
import com.orange.ru.domain.Scenario
import com.orange.ru.domain.product.ProductItem
import org.springframework.stereotype.Service
import com.orange.ru.domain.product.wrapper.WrapperString
import com.orange.ru.domain.product.wrapper.WrapperNumeric
import com.orange.ru.domain.product.wrapper.WrapperMoney
import com.orange.ru.service.SiteService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.orange.ru.service.PortService

@Service
class HandleScenarioImpl implements HandleScenario {
  static final Logger LOG = LoggerFactory.getLogger(HandleScenarioImpl.class);
  @Autowired FillIdentification fillIdentification
  @Autowired SiteService siteService
  @Autowired PortService portService

  @Override
  def calculate(Scenario scenario) {
    for (ProductItem item: scenario.productItems) {
      // Восстановление объектов Identification
      fillIdentification.calculate item
      for (WrapperString wrapper:item.strings)  wrapper.identification = item.identificationMap.get(wrapper.identification.code)
      for (WrapperNumeric wrapper:item.numerics)wrapper.identification = item.identificationMap.get(wrapper.identification.code)
      for (WrapperMoney wrapper:item.treasures) wrapper.identification = item.identificationMap.get(wrapper.identification.code)
      // Восстановление объектов site
      if (item.product.site.id != null) {
        LOG.debug("site was restored.")
        item.product.site = siteService.findById(item.product.site.id)
      }
      if (item.product.getClass().getSimpleName().equalsIgnoreCase("BusinessVpn")&&item.product.port.id != null){
        LOG.debug("port was restored.")
        item.product.port = portService.findById(item.product.port.id)
      }
    }
  }
}