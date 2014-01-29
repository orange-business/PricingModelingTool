package com.orange.ru.operation.ref

import org.springframework.stereotype.*
import com.orange.ru.domain.product.ProductItem
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.service.IdentificationService
import com.orange.ru.domain.product.*

@Service("fillIdentification")
@Repository
class FillIdentification {
  @Autowired IdentificationService identificationService
  def calculate = { ProductItem item ->
    if (item.product instanceof BusinessVpn) item.identificationMap = identificationService.getByNS("bvpn");
    else item.identificationMap = identificationService.getByNS("lines");
  }
}