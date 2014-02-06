package com.orange.ru.domain.product.json

import com.orange.ru.domain.*
import com.orange.ru.domain.product.*
import com.orange.ru.domain.product.enums.PortCoverage
import com.orange.ru.domain.product.enums.ProductItemType
import com.orange.ru.domain.product.wrapper.WrapperMoney
import com.orange.ru.domain.product.wrapper.WrapperNumeric
import com.orange.ru.domain.product.wrapper.WrapperString
import com.orange.ru.service.IdentificationService
import com.orange.ru.service.SiteService
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import groovy.json.JsonSlurper

@Service("scenarioDeserializer")
@Transactional
class ScenarioDeserializerImpl implements ScenarioDeserializer {
  @Autowired IdentificationService identificationService
  @Autowired SiteService siteService
  @Override
  Scenario deserialize(String json) {
    Scenario scenario = new Scenario()
    def slurper = new JsonSlurper()
    def result = slurper.parseText(json)
    // Заполняем сценарий
    scenario.id = result.id
    scenario.ownerEmail = result.ownerEmail
    scenario.note = result.note
    scenario.contractTerm = result.contractTerm
    scenario.lastUpdateDate = LocalDateTime.parse(result.lastUpdateDate, DateTimeFormat.forPattern("yyyy.MM.dd hh:mm"));


    for (def itemObj: result.items){
      Product product = null;
      // Создаем правильную услугу
      if (itemObj.product.code == "115.00") {
        product = new BusinessVpn()
        product.id = itemObj.product.id
        product.town = itemObj.product.town
        product.site = siteService.findById(itemObj.product.site.id)
        product.port = extractPort(itemObj)
      }
      if (itemObj.product.code == "802.01") {
        product = new AccessLines()

        product.id = itemObj.product.id
        product.site = siteService.findById(itemObj.product.site.id)
      }
      // добавляем полностью сконфигурированный item
      scenario.productItems.add(extractItem(itemObj, product))
    }

    return scenario
  }
  def extractPort(Object itemObj){
    def port = new Port()
    if (itemObj.product.port.coverage == "domestic") port.coverage = PortCoverage.DOMESTIC
    if (itemObj.product.port.coverage == "local") port.coverage = PortCoverage.LOCAL
    if (itemObj.product.port.coverage == "okrug") port.coverage = PortCoverage.OKRUG
    port.speed = itemObj.product.port.speed
    return port;
  }
  def extractItem(Object itemObj, Product product){
    // strings
    ProductItem item = new ProductItem(product)
    item.id = itemObj.id
    if (itemObj.type == "new") item.type = ProductItemType.NEW
    if (itemObj.type == "upgrade") item.type = ProductItemType.UPGRADE
    item.note = itemObj.note

    item.strings = extractStrings(itemObj, product.code)
    item.numerics = extractNumerics(itemObj, product.code)
    item.treasures = extractTreasures(itemObj, product.code)
    return item
  }
  def extractStrings(Object itemObj, String productCode){
    Set<WrapperString> strings = new HashSet<WrapperString>()
    for (def wrapper: itemObj.strings){
      Identification identification = identificationService.getByProductCode(productCode, wrapper.code)
      strings.add(new WrapperString(wrapper.value, identification))
    }
    return strings
  }
  def extractNumerics(Object itemObj, String productCode){
    Set<WrapperNumeric> numerics = new HashSet<WrapperNumeric>()
    for (def wrapper: itemObj.numerics){
      Identification identification = identificationService.getByProductCode(productCode, wrapper.code)
      numerics.add(new WrapperNumeric(wrapper.value, identification))
    }
    return numerics
  }
  def extractTreasures(Object itemObj, String productCode){
    Set<WrapperMoney> treasures = new HashSet<WrapperMoney>()
    for (def wrapper: itemObj.treasures){
      Identification identification = identificationService.getByProductCode(productCode, wrapper.code)
      treasures.add(new WrapperMoney(wrapper.value, identification))
    }
    return treasures
  }
}