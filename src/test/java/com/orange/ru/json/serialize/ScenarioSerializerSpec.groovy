package com.orange.ru.json.serialize

import spock.lang.Specification
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.slf4j.*
import com.orange.ru.service.ScenarioService
import com.orange.ru.domain.Scenario
import com.orange.ru.domain.product.json.ScenarioSerializer
import com.orange.ru.domain.product.BusinessVpn
import com.orange.ru.domain.product.ProductItem

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class ScenarioSerializerSpec extends Specification {
  static final Logger LOG = LoggerFactory.getLogger(ScenarioSerializerSpec.class)
  @Autowired ScenarioSerializer scenarioSerializer
  @Autowired ScenarioService scenarioService

  Scenario scenario; String json

  def setup(){
    LOG.debug("ScenarioSerializerSpec works.")
    scenario = (Scenario) scenarioService.findById(1)
    LOG.debug("Product is " + scenario.productItems.getAt(0).product);

    println "============================================================"
    println "productItems.size = " +  scenario.productItems.size();
    println "============================================================"
    Iterator<Integer> iterator2 = scenario.productItems.iterator();
    while(iterator2.hasNext()) {
      ProductItem item1 = iterator2.next();
      println "code is " +  item1.product.code
    }
    println "============================================================"

    LOG.debug("Port is " + scenario.productItems.getAt(0).product.port);
    LOG.debug("Site is " + ((BusinessVpn) scenario.productItems.getAt(0).product).site);
    json = scenarioSerializer.serialize(scenario)
  }
  def "Правильная сериализация вытащенного из базы сценария"() {
    LOG.debug(json)
    expect: true
  }
}