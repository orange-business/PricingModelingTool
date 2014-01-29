package com.orange.ru.heap.gson

import static java.lang.Double.parseDouble as parse
import com.google.gson.*
import com.orange.ru.core.exception.ProductConfigurationException
import com.orange.ru.domain.*
import com.orange.ru.domain.product.*
import com.orange.ru.domain.product.enums.*
import com.orange.ru.mongodb.repositories.BvpnCostsRepository
import com.orange.ru.operation.ref.FillIdentification
import com.orange.ru.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
//import com.orange.ru.domain.product.gson.GsonUtil
import java.nio.file.*

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class ScenarioLinesSerializeSpec extends spock.lang.Specification {
  Properties props; Scenario scenario; String json
  @Autowired CustomerService customerService
  @Autowired SiteService siteService
  @Autowired BvpnCostsRepository bvpnCostsRepository
  @Autowired FillIdentification fillIdentification

  def setup(){
    props = getProps("test_product/lines/podbor_13.11.05_01.txt")
    // Конфигурирование услуги
    scenario = new Scenario()
    scenario.note = 'Тестовое сообщение'
    scenario.ownerEmail = 'sergey.bogachek@orange.com'

    AccessLines product = new AccessLines()
    def customer = customerService.findById(3)
    product.site = siteService.findByCustomer(customer).get(0)
    product.type = LinesType.RADIO

    ProductItem item = new ProductItem(product, scenario)
    fillIdentification.calculate item
    item.addString("in_lines_type_string", product.type.code)

    scenario.contractTerm = Integer.parseInt(props.get("in_contract_term_months"))
    // item.type
    switch (((String) props.get("in_product_item_type_string")).toLowerCase()) {
      case "new": item.type = ProductItemType.NEW; break
      case "upgrade": item.type = ProductItemType.UPGRADE; break
      case "degrade": item.type = ProductItemType.DEGRADE; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item.addDouble("in_contract_term_months", item.scenario.contractTerm)
    item.addString("in_product_item_type_string", item.type.code)
    item.addMoney("in_capex_equipment_money",parse(props.get("in_capex_equipment_money")))
    item.addMoney("in_capex_build_money",    parse(props.get("in_capex_build_money")))
    item.addMoney("in_cost_onetime_lease_money", parse(props.get("in_cost_onetime_lease_money")))
    item.addMoney("in_cost_monthly_lease_money", parse(props.get("in_cost_monthly_lease_money")))
    item.addMoney("in_cost_monthly_build_money", parse(props.get("in_cost_monthly_build_money")))
    // заданное значение по которому надо вычислить разовый платеж
    item.addMoney("inout_required_price_payment_monthly_money", parse(props.get("inout_required_price_payment_monthly_money")))

    item.addDouble("in_contract_term_months", scenario.contractTerm)
    item.addString("in_product_item_type_string", item.type.code)

    def opportunity = new Opportunity()
    opportunity.closed = false
    opportunity.externalId = 23765498
    opportunity.customer = customer
    opportunity.scenarios.add(item.scenario)
  }
  def "Превращение готового Scenario в Json"() {
    when: json = GsonUtil.gson.toJson(scenario)

    JsonParser parser = new JsonParser();
    JsonElement o1 = parser.parse(json);

    Path resPath = Paths.get(ScenarioLinesSerializeSpec.class.getResource("../test3.json").toURI());
    String json1  = new String(Files.readAllBytes(resPath), "UTF8");
    JsonElement o2 = parser.parse(json1)

    then:  o1 == o2
  }
  def Closure getProps = { path ->
    def props = new Properties()
    (new File(path).getText("UTF-8")).eachLine { line ->
      def index = line.indexOf(' ')
      if (index > 2) props.put(line.substring(0,index), line.substring(index+1))
    }
    return props
  }
}