package com.orange.ru.json.serialize

import static java.lang.Double.parseDouble as parse
import com.orange.ru.core.exception.ProductConfigurationException
import com.orange.ru.domain.*
import com.orange.ru.domain.product.*
import com.orange.ru.domain.product.enums.*
import com.orange.ru.mongodb.repositories.BvpnCostsRepository
import com.orange.ru.operation.bvpn.FixRequiredPriceOperation
import com.orange.ru.operation.ref.FillIdentification
import com.orange.ru.service.CustomerService
import com.orange.ru.service.SiteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import javax.persistence.PersistenceContext
import javax.persistence.EntityManager
import com.orange.ru.domain.product.json.ScenarioSerializer
import org.springframework.test.annotation.Rollback
import org.slf4j.*
import com.orange.ru.operation.bvpn.UbbRequiredPriceOperation

import com.orange.ru.operation.lines.LinesStandardPriceOperation
import com.orange.ru.operation.lines.LinesRequiredPriceFixedMonthlyAndOnetimeOperation

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class ScenarioBvpnToDbSpec extends spock.lang.Specification {
  static final Logger LOG = LoggerFactory.getLogger(ScenarioBvpnToDbSpec.class)
  @PersistenceContext EntityManager em
  @Autowired ScenarioSerializer scenarioSerializer
  Scenario scenario; Customer customer; List<Site> sites
  @Autowired CustomerService customerService
  @Autowired SiteService siteService
  @Autowired BvpnCostsRepository bvpnCostsRepository
  @Autowired FixRequiredPriceOperation bvpn_fix_required_price_operation
  @Autowired UbbRequiredPriceOperation bvpn_ubb_required_price_operation
  @Autowired FillIdentification fillIdentification
  @Autowired LinesStandardPriceOperation lines_standard_price_operation
  @Autowired LinesRequiredPriceFixedMonthlyAndOnetimeOperation lines_required_price_operation

  def setup(){
    customer = customerService.findById(3)  // Клиент: Customer(ОАО "Янтарный сказ")
    sites = siteService.findByCustomer(customer)
    scenario = new Scenario()
    scenario.note = 'Тестовое сообщение'
    scenario.ownerEmail = 'sergey.bogachek@orange.com'
    addItem1(getProps("test_product/bvpn/fix/required_price_fix_test01.txt"))
    addItem2(getProps("test_product/bvpn/ubb/required_price_ubb_test02.txt"))
    addItem3(getProps("test_product/lines/podbor_13.11.05_01.txt"))
  }
  @Rollback(false)
  def "Превращение готового Scenario в Json"() {
    when: String json = scenarioSerializer.serialize(scenario)
    LOG.debug(json)
    println json
    em.persist(scenario)
    em.flush()
    then: true
  }
  def addItem3(Properties props){
    ProductItem item = new ProductItem(new AccessLines())
    item.product.site = sites.get(2)
    scenario.productItems.add(item)
    fillIdentification.calculate item

    item.addDouble("in_contract_term_months", scenario.contractTerm)
    // item.type
    switch (((String) props.get("in_product_item_type_string")).toLowerCase()) {
      case "new": item.type = ProductItemType.NEW; break
      case "upgrade": item.type = ProductItemType.UPGRADE; break
      case "degrade": item.type = ProductItemType.DEGRADE; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item.addString("in_product_item_type_string", item.type.code)
    item.addMoney("in_capex_equipment_money", parse(props.get("in_capex_equipment_money")))
    item.addMoney("in_capex_build_money", parse(props.get("in_capex_build_money")))

    // lines_type
    switch (((String) props.get("in_lines_type_string")).toLowerCase()) {
      case "build": item.product.type = LinesType.BUILD; break
      case "lease": item.product.type = LinesType.LEASE; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item.addString("in_lines_type_string", item.product.type.code)
    item.addMoney("in_cost_onetime_lease_money", parse(props.get("in_cost_onetime_lease_money")))
    item.addMoney("in_cost_monthly_lease_money", parse(props.get("in_cost_monthly_lease_money")))
    item.addMoney("in_cost_monthly_build_money", parse(props.get("in_cost_monthly_build_money")))
    item.addMoney("inout_required_price_payment_onetime_money", parse(props.get("inout_required_price_payment_onetime_money")))
    item.addMoney("inout_required_price_payment_monthly_money", parse(props.get("inout_required_price_payment_monthly_money")))

    lines_standard_price_operation.call item
    lines_required_price_operation.call item
  }
  def addItem2(Properties props){
    ProductItem item1 = new ProductItem(new BusinessVpn())
    scenario.productItems.add(item1)
    fillIdentification.calculate item1
    // item.type
    switch (((String) props.get("in_product_item_type_string")).toLowerCase()) {
      case "new": item1.type = ProductItemType.NEW; break
      case "upgrade": item1.type = ProductItemType.UPGRADE; break
      case "degrade": item1.type = ProductItemType.DEGRADE; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item1.addDouble("in_contract_term_months", scenario.contractTerm)
    item1.addString("in_product_item_type_string", item1.type.code)

    item1.product.site = sites.get(1)
    item1.product.town = bvpnCostsRepository.getTownByName(props.get("in_town_string"))
    item1.addString("in_town_string", item1.product.town)
    item1.product.port = new Port()
    // port.coverage
    switch (((String) props.get("in_port_coverage_string")).toLowerCase() ) {
      case "domestic": item1.product.port.coverage = PortCoverage.DOMESTIC; break
      case "local": item1.product.port.coverage = PortCoverage.LOCAL; break
      case "okrug": item1.product.port.coverage = PortCoverage.OKRUG; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item1.addString("in_port_coverage_string", item1.product.port.coverage.code)

    // item.product.port.tarifficationScheme
    switch (((String) props.get("in_tariffication_scheme_string")).toLowerCase() ) {
      case "fix": item1.product.port.tarifficationScheme = PortTarifficationScheme.FIX; break
      case "ubb": item1.product.port.tarifficationScheme = PortTarifficationScheme.UBB; break
      default: throw new ProductConfigurationException("in_tariffication_scheme_string is broken")
    }
    // in_port_type
    switch ( ((String) props.get("in_port_type_string")).toLowerCase()) {
      case "gold": item1.product.port.type = PortType.GOLD; break
      case "silver": item1.product.port.type = PortType.SILVER; break
      case "platinum": item1.product.port.type = PortType.PLATINUM; break
      default: throw new ProductConfigurationException("in_port_type_string is broken")
    }
    item1.product.port.speed = Integer.parseInt(props.get("in_speed_number"))

    item1.addDouble("ref_cost_monthly_port_support_percent", 0.00125)
    item1.addMoney("in_required_price_ubb_payment_monthly_money", parse(props.get("in_required_price_ubb_payment_monthly_money")))
    item1.addMoney("in_required_price_ubb_payment_onetime_money", parse(props.get("in_required_price_ubb_payment_onetime_money")))
    bvpn_ubb_required_price_operation.call item1
  }
  def addItem1(Properties props){
    ProductItem item = new ProductItem(new BusinessVpn())
    scenario.productItems.add(item)
    fillIdentification.calculate item
    scenario.contractTerm = Integer.parseInt(props.get("in_contract_term_months"))
    // item.type
    switch (((String) props.get("in_product_item_type_string")).toLowerCase()) {
      case "new": item.type = ProductItemType.NEW; break
      case "upgrade": item.type = ProductItemType.UPGRADE; break
      case "degrade": item.type = ProductItemType.DEGRADE; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item.addDouble("in_contract_term_months", scenario.contractTerm)
    item.addString("in_product_item_type_string", item.type.code)

    item.product.site = sites.get(0)
    item.product.town = bvpnCostsRepository.getTownByName(props.get("in_town_string"))
    item.addString("in_town_string", item.product.town)
    item.product.port = new Port()
    // port.coverage
    switch (((String) props.get("in_port_coverage_string")).toLowerCase() ) {
      case "domestic": item.product.port.coverage = PortCoverage.DOMESTIC; break
      case "local": item.product.port.coverage = PortCoverage.LOCAL; break
      case "okrug": item.product.port.coverage = PortCoverage.OKRUG; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item.addString("in_port_coverage_string", item.product.port.coverage.code)

    // item.product.port.tarifficationScheme
    switch (((String) props.get("in_tariffication_scheme_string")).toLowerCase() ) {
      case "fix": item.product.port.tarifficationScheme = PortTarifficationScheme.FIX; break
      case "ubb": item.product.port.tarifficationScheme = PortTarifficationScheme.UBB; break
      default: throw new ProductConfigurationException("in_tariffication_scheme_string is broken")
    }
    // in_port_type
    switch ( ((String) props.get("in_port_type_string")).toLowerCase()) {
      case "gold": item.product.port.type = PortType.GOLD; break
      case "silver": item.product.port.type = PortType.SILVER; break
      case "platinum": item.product.port.type = PortType.PLATINUM; break
      default: throw new ProductConfigurationException("in_port_type_string is broken")
    }
    item.product.port.speed = Integer.parseInt(props.get("in_speed_number"))

    item.addMoney("in_required_price_fix_payment_onetime_money", parse(props.get("in_required_price_fix_payment_onetime_money")))
    item.addMoney("in_required_price_fix_payment_monthly_money", parse(props.get("in_required_price_fix_payment_monthly_money")))

    bvpn_fix_required_price_operation.call item
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