package com.orange.ru.domain.product.json

import com.orange.ru.domain.*
import com.orange.ru.domain.product.*
import com.orange.ru.domain.product.enums.LinesType
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
    def parsed = (new JsonSlurper()).parseText(json)
    Set productItems = new HashSet<ProductItem>();
    for (def item : parsed.items){
      Product product = null;
      // Создаем правильную услугу
      if (item.product.code == "115.00") product = extractBusinessVpn(item)
      if (item.product.code == "802.01") product = extractAccessLines(item)
      // добавляем полностью сконфигурированный item
      productItems.add(extractItem(item, product))
    }
    // Заполняем сценарий
    return extractScenario(parsed, productItems)
  }
  def extractScenario(def parsed, def productItems){
    Scenario scenario = new Scenario()
    scenario.productItems = productItems
    scenario.id = parsed.id
    scenario.ownerEmail = parsed.ownerEmail
    scenario.note = parsed.note
    scenario.contractTerm = parsed.contractTerm
    scenario.lastUpdateDate = LocalDateTime.parse(parsed.lastUpdateDate, DateTimeFormat.forPattern("yyyy.MM.dd hh:mm"))
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
  def extractAccessLines(def item){
    AccessLines product = new AccessLines()
    product.id = item.product.id
    if (item.product.type == "build") product.type = LinesType.BUILD
    if (item.product.type == "lease") product.type = LinesType.LEASE
    product.site = siteService.findById(item.product.site.id)
    return product
  }
  def extractBusinessVpn(def item){
    BusinessVpn product = new BusinessVpn()
    product.id = item.product.id
    product.town = item.product.town
    product.site = siteService.findById(item.product.site.id)
    product.port = extractPort(item)
    return product
  }
}