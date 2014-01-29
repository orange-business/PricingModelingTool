package com.orange.ru.operation.bvpn

import com.orange.ru.core.exception.ProductConfigurationException
import com.orange.ru.domain.product.*
import com.orange.ru.operation.ref.FillIdentification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import com.orange.ru.service.*
import com.orange.ru.domain.*
import com.orange.ru.mongodb.repositories.BvpnCostsRepository
import static java.lang.Double.parseDouble as parse
import static org.apache.commons.math3.util.Precision.equals
import com.orange.ru.domain.product.enums.ProductItemType
import com.orange.ru.domain.product.enums.PortCoverage
import com.orange.ru.domain.product.enums.PortTarifficationScheme
import com.orange.ru.domain.product.enums.PortType

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
class FixStandardPriceSpec extends spock.lang.Specification {
  Properties props; ProductItem item
  @Autowired CustomerService customerService
  @Autowired SiteService siteService
  @Autowired BvpnCostsRepository bvpnCostsRepository
  @Autowired FixStandardPriceOperation standard_price_operation
  @Autowired FillIdentification fillIdentification

  def setup(){
    props = getProps("test_product/bvpn/fix/test01.txt")
    // Конфигурирование услуги
    if (!"FIX".equalsIgnoreCase(props.get("in_tariffication_scheme_string"))) throw new ProductConfigurationException("Must be FIX!")

    item = new ProductItem(new BusinessVpn(), new Scenario())
    fillIdentification.calculate item
    item.scenario.contractTerm = Integer.parseInt(props.get("in_contract_term_months"))
    // item.type
    switch (((String) props.get("in_product_item_type_string")).toLowerCase()) {
      case "new": item.type = ProductItemType.NEW; break
      case "upgrade": item.type = ProductItemType.UPGRADE; break
      case "degrade": item.type = ProductItemType.DEGRADE; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item.addDouble("in_contract_term_months", item.scenario.contractTerm)
    item.addString("in_product_item_type_string", item.type.code)
    def customer = customerService.findById(3)  // Клиент: Customer(ОАО "Янтарный сказ")
    def opportunity = new Opportunity()
    opportunity.closed = false
    opportunity.externalId = 23765498
    opportunity.customer = customer
    opportunity.scenarios.add(item.scenario)
    def site = siteService.findByCustomer(customer).get(0)
    item.product.site = site
    item.product.town = bvpnCostsRepository.getTownByName(props.get("in_town_string"))
    item.product.port = new Port()
    // port.coverage
    switch (((String) props.get("in_port_coverage_string")).toLowerCase() ) {
      case "domestic": item.product.port.coverage = PortCoverage.DOMESTIC; break
      case "local": item.product.port.coverage = PortCoverage.LOCAL; break
      case "okrug": item.product.port.coverage = PortCoverage.OKRUG; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
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

    standard_price_operation.call item
  }
  def "Затраты за порт (разово)"() {
    expect: item.get("out_cost_onetime_money").amount == parse(props.get("out_cost_onetime_money"))
  }
  def "Расчет ежемесячного платежа за порт"() {
    expect: item.get("out_standard_price_fix_payment_monthly_money").amount == parse(props.get("out_standard_price_fix_payment_monthly_money"))
  }
  def "Расчет ежемесячных затрат (костов) за порт"() {
    expect: equals(item.get("out_standard_cost_monthly_money").amount, parse(props.get("out_standard_cost_monthly_money")), 1.0)
  }
  def "Стандартная цена (FIX), маржа"() {
    expect: equals(item.get("out_standard_price_fix_margin_percent"), parse(props.get("out_standard_price_fix_margin_percent")), 1.0)
  }
  def "разовый платеж для текущей скорости от 64K до 1000M"() {
    expect: item.get("refout_payment_onetime_speed_current_money").amount == parse(props.get("refout_payment_onetime_speed_current_money"))
  }
  def cleanup() {
    println """
         GM = 1 - (OTC  x (1+ R) + N x MRC x  (1+ R)) / (OTP x (1-R) + N x MRP * (1-R))
    """
    println "r = " + item.get("ref_risk_percent")
    println "N = " + item.get("in_contract_term_months")
    println "mrc = " + item.get("out_standard_cost_monthly_money")
    println "otc = " + item.get("out_cost_onetime_money")
    println "otp = " + item.get("out_standard_price_payment_onetime_money")
    println "mrp = " + item.get("out_standard_price_fix_payment_monthly_money")
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