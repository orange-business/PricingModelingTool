package com.orange.ru.json.serialize

import static java.lang.Double.parseDouble as parse
import com.orange.ru.core.exception.ProductConfigurationException
import com.orange.ru.domain.*
import com.orange.ru.domain.product.*
import com.orange.ru.domain.product.enums.*
import com.orange.ru.domain.product.json.ScenarioSerializer
import com.orange.ru.mongodb.repositories.BvpnCostsRepository
import com.orange.ru.operation.bvpn.FixRequiredPriceOperation
import com.orange.ru.operation.ref.FillIdentification
import com.orange.ru.service.*
import org.slf4j.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class AddBvpnToExistingScenarioToDbSpec extends spock.lang.Specification {
  static final Logger LOG = LoggerFactory.getLogger(AddBvpnToExistingScenarioToDbSpec.class)
  @PersistenceContext EntityManager em;
  @Autowired ScenarioSerializer scenarioSerializer
  Properties props; Scenario scenario; ProductItem item; String json
  @Autowired CustomerService customerService
  @Autowired SiteService siteService
  @Autowired ScenarioService scenarioService
  @Autowired BvpnCostsRepository bvpnCostsRepository
  @Autowired FixRequiredPriceOperation required_price_operation
  @Autowired FillIdentification fillIdentification

  def setup(){
    props = getProps("test_product/bvpn/fix/required_price_fix_test01.txt")
    scenario = scenarioService.findById(1)
    item = new ProductItem(new BusinessVpn())
    scenario.productItems.add(item)
    fillIdentification.calculate item
    // item.type
    switch (((String) props.get("in_product_item_type_string")).toLowerCase()) {
      case "new": item.type = ProductItemType.NEW; break
      case "upgrade": item.type = ProductItemType.UPGRADE; break
      case "degrade": item.type = ProductItemType.DEGRADE; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item.addDouble("in_contract_term_months", scenario.contractTerm)
    item.addString("in_product_item_type_string", item.type.code)
    def customer = customerService.findById(5)

    item.product.site = siteService.findByCustomer(customer).get(0)


    println item.product.site.id

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

    required_price_operation.call item
    // Превращение готового ProductItem в Json и обратно
  }
  @Rollback(false)
  def "Превращение готового Scenario в Json"() {
    when:
    em.persist(scenario)

    em.flush()
    then: true
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