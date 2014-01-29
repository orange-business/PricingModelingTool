package com.orange.ru.heap.gson

import com.orange.ru.domain.*
import com.orange.ru.domain.product.ProductItem
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.domain.product.renew.HandleScenario
//import com.orange.ru.domain.product.gson.GsonUtil
import java.nio.file.*

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class ScenarioPrimarilyDeserializeBvpnSpec extends spock.lang.Specification {
  @Autowired HandleScenario handleScenario
  Scenario recoved

  Path resPath = Paths.get(ScenarioPrimarilyDeserializeBvpnSpec.class.getResource("../test2.json").toURI());
  String json  = new String(Files.readAllBytes(resPath), "UTF8");

  def setup(){
    recoved = GsonUtil.gson.fromJson(json, Scenario.class)
    handleScenario.calculate recoved

    println "recoved.productItems.size -> " + recoved.getProductItems().size()
    Iterator iterator = recoved.getProductItems().iterator()
    while (iterator.hasNext()){
      ProductItem next = iterator.next()
      println "product.name -> " + next.product.name
      println "next.numerics.size -> " + next.numerics.size()
    }
  }
  def "Превращение готового json в Scenario"() {
    println  "in_product_item_type_string -> " + recoved.productItems.getAt(0).identificationMap.get("in_product_item_type_string").code
    println "lastUpdateDate -> " + recoved.lastUpdateDate

    expect: recoved.productItems.getAt(0).identificationMap.get("in_product_item_type_string")
  }
}