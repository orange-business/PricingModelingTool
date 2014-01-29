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

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
class FixRequiredPriceSpec extends spock.lang.Specification {
  Properties props; ProductItem item
  @Autowired CustomerService customerService
  @Autowired SiteService siteService
  @Autowired BvpnCostsRepository bvpnCostsRepository
  @Autowired FixRequiredPriceOperation required_price_operation
  @Autowired FillIdentification fillIdentification

  def setup(){
    props = getProps("test_product/bvpn/fix/required_price_fix_test01.txt")
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
      case "domestic": item.product.port.coverage = Port.Coverage.DOMESTIC; break
      case "local": item.product.port.coverage = Port.Coverage.LOCAL; break
      case "okrug": item.product.port.coverage = Port.Coverage.OKRUG; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    // item.product.port.tarifficationScheme
    switch (((String) props.get("in_tariffication_scheme_string")).toLowerCase() ) {
      case "fix": item.product.port.tarifficationScheme = Port.TarifficationScheme.FIX; break
      case "ubb": item.product.port.tarifficationScheme = Port.TarifficationScheme.UBB; break
      default: throw new ProductConfigurationException("in_tariffication_scheme_string is broken")
    }
    // in_port_type
    switch ( ((String) props.get("in_port_type_string")).toLowerCase()) {
      case "gold": item.product.port.type = Port.Type.GOLD; break
      case "silver": item.product.port.type = Port.Type.SILVER; break
      case "platinum": item.product.port.type = Port.Type.PLATINUM; break
      default: throw new ProductConfigurationException("in_port_type_string is broken")
    }
    item.product.port.speed = Integer.parseInt(props.get("in_speed_number"))

    item.addMoney("in_required_price_fix_payment_onetime_money", parse(props.get("in_required_price_fix_payment_onetime_money")))
    item.addMoney("in_required_price_fix_payment_monthly_money", parse(props.get("in_required_price_fix_payment_monthly_money")))

    required_price_operation.call item
  }
  def "Запрошенная цена (FIX), скидка от стандартной цены, разово"() {
    expect: equals(item.get("out_required_price_fix_discount_onetime_percent"), parse(props.get("out_required_price_fix_discount_onetime_percent")), 1.0)
  }
  def "Запрошенная цена (FIX), скидка от стандартной цены, ежемесячно"() {
    expect: equals(item.get("out_required_price_fix_discount_monthly_percent"), parse(props.get("out_required_price_fix_discount_monthly_percent")), 1.0)
  }
  def "Запрошенная цена (FIX), маржа"() {
    expect: equals(item.get("out_required_price_fix_margin_percent"), parse(props.get("out_required_price_fix_margin_percent")), 1.0)
  }

  def "Затраты за порт (ежемесячно)"() {
    expect: equals(item.get("out_required_cost_monthly_money").amount, parse(props.get("out_required_cost_monthly_money")), 1.0)
  }
//  def "Стоимость 1 человеко-часа работы специалиста FO"() {
//    expect: item.get("ref_cost_hour_FO_money").amount == parse(props.get("ref_cost_hour_FO_money"))
//  }
  def cleanup() {
//    println "in_required_price_fix_payment_onetime_money = " + item.get("in_required_price_fix_payment_onetime_money")
//    println "out_standard_price_payment_onetime_money = " + item.get("out_standard_price_payment_onetime_money")
//    println "out_required_cost_monthly_money = " + item.get("out_required_cost_monthly_money")
//
//    println "Проверка ========================================================================================"
//
//    // out_cost_monthly_helpdesk_support_port_money
//    println "Поддержка HELP DESK стоимость на порт ежемесячно = " + item.get("out_cost_monthly_helpdesk_support_port_money")
//    println "ref_cost_hour_FO_money = " + item.get("ref_cost_hour_FO_money") //
//    println "ref_cost_hour_HD_money = " + item.get("ref_cost_hour_HD_money")
//    println "ref_cost_onetime_upgrade_port_hours = " + item.get("ref_cost_onetime_upgrade_port_hours")
//    println "ref_cost_monthly_helpdesk_support_port_hour = " + item.get("ref_cost_monthly_helpdesk_support_port_hour")
//    println "ref_cost_monthly_port_support_percent = " + item.get("ref_cost_monthly_port_support_percent")
//    println "ref_cost_monthly_depreciation_router_1kb_sec_money = " + item.get("ref_cost_monthly_depreciation_router_1kb_sec_money")
//    println "ref_cost_monthly_depreciation_port_money = " + item.get("ref_cost_monthly_depreciation_port_money")
//    println "ref_utilization_number = " + item.get("ref_utilization_number")
//    println "ref_mux_number = " + item.get("ref_mux_number")
//    println "ref_cost_monthly_transmission_cost_1kb_money = " + item.get("ref_cost_monthly_transmission_cost_1kb_money")
//    println "out_cost_traffic_1MB_money = " + item.get("out_cost_traffic_1MB_money")

//    println "Проверка 2 ========================================================================================"
//    println "out_cost_monthly_transmission_cost_1kb_money = " + item.get("out_cost_monthly_transmission_cost_1kb_money")
//    println "ref_cost_monthly_depreciation_router_1kb_sec_money = " + item.get("ref_cost_monthly_depreciation_router_1kb_sec_money")
//    println "item.product.port.speed = " + item.product.port.speed
//    println "in_required_price_fix_payment_monthly_money = " + item.get("in_required_price_fix_payment_monthly_money")
//    println "ref_cost_monthly_port_support_ratio = " + item.get("ref_cost_monthly_port_support_ratio")
//    println "out_cost_monthly_helpdesk_support_port_money = " + item.get("out_cost_monthly_helpdesk_support_port_money")
//    println "ref_cost_monthly_depreciation_port_money = " + item.get("ref_cost_monthly_depreciation_port_money")
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