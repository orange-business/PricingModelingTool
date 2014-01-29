package com.orange.ru.operation.lines

import com.orange.ru.core.exception.ProductConfigurationException
import com.orange.ru.domain.Scenario
import com.orange.ru.domain.product.*
import com.orange.ru.operation.ref.FillIdentification
import com.orange.ru.util.ParseToProps
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import static org.apache.commons.math3.util.Precision.equals
import static Double.parseDouble as parse
import com.orange.ru.domain.product.enums.ProductItemType

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
class RequiredPriceFixedOnetimeSpec extends Specification {
  Properties props; ProductItem item
  @Autowired LinesRequiredPriceFixedOnetimeOperation required_price_operation
  @Autowired FillIdentification fillIdentification

  def setup(){
    props = new ParseToProps().parse("test_product/lines/podbor_13.11.05_01.txt")
    // Конфигурирование услуги

    item = new ProductItem(new AccessLines(), new Scenario())
    fillIdentification.calculate item
    item.scenario.contractTerm = Integer.parseInt(props.get("in_contract_term_months"))
    // item.type
    switch (((String) props.get("in_product_item_type_string")).toLowerCase()) {
      case "new": item.type = ProductItemType.NEW; break
      case "upgrade": item.type = ProductItemType.UPGRADE; break
      case "degrade": item.type = ProductItemType.DEGRADE; break
      default: throw new ProductConfigurationException("in_product_item_type_string is broken")
    }
    item.addDouble(item.scenario.contractTerm, "in_contract_term_months")
    item.addString(item.type.code, "in_product_item_type_string")
    item.addMoney("in_capex_equipment_money",    parse(props.get("in_capex_equipment_money")))
    item.addMoney("in_capex_build_money",        parse(props.get("in_capex_build_money")))
    item.addMoney("in_cost_onetime_lease_money", parse(props.get("in_cost_onetime_lease_money")))
    item.addMoney("in_cost_monthly_lease_money", parse(props.get("in_cost_monthly_lease_money")))
    item.addMoney("in_cost_monthly_build_money", parse(props.get("in_cost_monthly_build_money")))
    // заданное значение по которому надо вычислить ежемесячный платеж
    item.addMoney("inout_required_price_payment_onetime_money", parse(props.get("inout_required_price_payment_onetime_money")))

    required_price_operation.call item
  }
  def "Адекватны ли тестовые данные?"() {
    expect:
    item.get("out_cost_monthly_money").amount == parse(props.get("out_cost_monthly_money"))
    item.get("out_cost_capex_onetime_money").amount == parse(props.get("out_cost_capex_onetime_money"))
  }

  def "Желаемая цена, ежемесячный платеж (разовый задан)"() {
    expect: equals(item.get("inout_required_price_payment_monthly_money").amount, parse(props.get("inout_required_price_payment_monthly_money")), 1)
  }
  def "Скидка от стандартной цены, разово"() {
    expect: equals(item.get("out_required_price_discount_onetime_percent"), parse(props.get("out_required_price_discount_onetime_percent")), 1)
  }
  def "Скидка от стандартной цены, ежемесячно"() {
    expect: equals(item.get("out_required_price_discount_monthly_percent"), parse(props.get("out_required_price_discount_monthly_percent")), 0.5)
  }
  def "Стандартная цена, ежемесячный платеж"() {
    expect: equals(item.get("out_standard_price_payment_monthly_money").amount, parse(props.get("out_standard_price_payment_monthly_money")), 1)
  }
  def "Стандартная цена, разовый платеж"() {
    expect: equals(item.get("out_standard_price_payment_onetime_money").amount, parse(props.get("out_standard_price_payment_onetime_money")), 1)
  }
  def cleanup() {
    // println "inout_required_price_payment_onetime_money = " + item.get("inout_required_price_payment_onetime_money")
  }
}