package com.orange.ru.operation.bvpn

import com.orange.ru.domain.product.ProductItem
import com.orange.ru.operation.ref.RefRiskPercent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.Transactional
import com.orange.ru.mongodb.repositories.*
import com.orange.ru.operation.bvpn.ref.RefMuxNumber
import org.joda.money.BigMoney
import static java.math.RoundingMode.*
import com.orange.ru.operation.bvpn.ref.*
/**
 * Здесь единый расчет стандартной цены для fix схемы тарификации.
 */
@Service("standard_price_fix_operation")
@Repository
@Transactional(readOnly = true)
class FixStandardPriceOperationImpl implements FixStandardPriceOperation {
  @Autowired RefRiskPercent ref_risk_percent
  @Autowired RefCoeffTariffCalculationForSpeed ref_coeff_tariff_calculation_for_speed_number
  @Autowired RefCostMonthlyDepreciationRouter1kbSec ref_cost_monthly_depreciation_router_1kb_sec_money
  @Autowired RefStandardPricePayment_2M_Monthly ref_standard_price_payment_2M_monthly_money
  @Autowired RefCostMonthlyTransmissionCost1kb ref_cost_monthly_transmission_cost_1kb_money
  @Autowired RefCostMonthlyDepreciationPort ref_cost_monthly_depreciation_port_money
  @Autowired RefCostMonthlyPortSupportPercent ref_cost_monthly_port_support_percent
  @Autowired RefUtilizationNumber ref_utilization_number
  @Autowired RefMuxNumber ref_mux_number
  @Autowired HourRepository hourRepository
  @Autowired InstallationRepository installationRepository

  void call(ProductItem item){
    ref_risk_percent.calculate item
    ref_coeff_tariff_calculation_for_speed_number.calculate item
    ref_cost_monthly_depreciation_router_1kb_sec_money.calculate item
    ref_cost_monthly_port_support_percent.calculate item
    ref_cost_monthly_depreciation_port_money.calculate item
    ref_standard_price_payment_2M_monthly_money.calculate item
    ref_cost_monthly_transmission_cost_1kb_money.calculate item
    ref_utilization_number.calculate item
    ref_mux_number.calculate item

    // "Поддержка HELP DESK количество человеко-часов работы (на порт)
    item.addDouble("ref_cost_monthly_helpdesk_support_port_hour", 0.39)

    // Поддержка HELP DESK стоимость на порт ежемесячно
    item.addMoney("out_cost_monthly_helpdesk_support_port_money",
        hourRepository.getMoneyByCost("CostCS").multipliedBy(item.get("ref_cost_monthly_helpdesk_support_port_hour")))

    // Выбор разового платежа по типу запроса, скорости порта и сроку контракта из справочника "Базовые ежемесячные тарифы IP VPN"
    item.addMoney("refout_payment_onetime_speed_current_money",installationRepository.getMoneyByItem(item, (int) item.getDouble("in_contract_term_months")))

    // Стандартная цена, разовый платеж
    item.addMoney("out_standard_price_payment_onetime_money", installationRepository.getMoneyByItem(item, (int) item.getDouble("in_contract_term_months")))

    // Затраты за порт (разово), равняется суммарной трате на работу специалиста = количество часов * стоимость труда специалиста
    item.addMoney("out_cost_onetime_money", hourRepository.calculateByCostAndProductItemType("CostFO", item))

    // Расчет ежемесячного платежа за порт
    item.addMoney("out_standard_price_fix_payment_monthly_money", calc2(item))

    // Расчет коста IP VPN за 1kbit
    item.addMoney("out_cost_monthly_transmission_cost_1kb_money", calc3(item))

    // Расчет коста IP VPN за 1 Мегабайт
    item.addMoney("out_cost_monthly_transmission_cost_1MB_money", calc1(item))

    // Расчет ежемесячных затрат (костов) за порт
    item.addMoney("out_standard_cost_monthly_money", calc(item))

    // Стандартная цена (FIX)	Маржа
    item.addDouble("out_standard_price_fix_margin_percent", fit(item))
  }
  def static Closure calc3 = { item ->
    double m1 = item.get("ref_cost_monthly_transmission_cost_1kb_money").amount
    double u =  item.get("ref_utilization_number")
    double mux = item.get("ref_mux_number")
    double res = m1/(u*mux)
    BigMoney.parse("RUR " + res.round(4))
  }
  def static Closure calc2 = { item ->
    BigMoney money1 = item.get("ref_standard_price_payment_2M_monthly_money").multipliedBy(item.get("ref_coeff_tariff_calculation_for_speed_number"))
    (money1.getAmount().intValue() < 1000)? BigMoney.parse("RUR 1000"): money1.rounded(-2, CEILING)
  }
  def static Closure calc1 = { item ->
    item.get("out_cost_monthly_transmission_cost_1kb_money").dividedBy(0.30899,CEILING).dividedBy(1024.0,CEILING)
  }
  def static Closure calc = { item ->
    /* mrc_pe = mrc_pe_1Кbit* Capacity  + mrc_deprouter_Kbit* Capacity  + mrp_pe * CoeffExtNetworkSup + mrc_supportHD + mrc_depaccessport
       где mrc_pe - стандартные ежемесячный платеж за порт IP VPN, mrc_pe_1Кbit - кост IP VPN за 1 Кбит */
    (item.get("out_cost_monthly_transmission_cost_1kb_money").multipliedBy((Long) item.product.port.speed) +
        item.get("ref_cost_monthly_depreciation_router_1kb_sec_money").multipliedBy((Long) item.product.port.speed) +
        item.get("out_standard_price_fix_payment_monthly_money").multipliedBy(item.get("ref_cost_monthly_port_support_ratio")) +
        item.get("out_cost_monthly_helpdesk_support_port_money") +
        item.get("ref_cost_monthly_depreciation_port_money")).rounded(2, CEILING)
  }
  def static Closure fit = { item ->
    /* Формулы расчет маржи (): GM = 1 - (OTC  x (1+ R) + N x MRC x  (1+ R)) / (OTP x (1-R) + N x MRP * (1-R))
      где GM (Gross Margin) – маржа.
      Параметры расчета: OTP – разовые платежи (тариф) за порт IP VPN, MRP – ежемесячные платежи (тариф) за порт IP VPN.
      Статьи затрат: OTC - разовые затраты за порт IP VPN, MRC - ежемесячные затраты за порт IP VPN. */
    Double r = item.get("ref_risk_ratio"), N = item.get("in_contract_term_months")
    Double mrc = item.get("out_standard_cost_monthly_money").amount, otc = item.get("out_cost_onetime_money").amount
    Double otp = item.get("out_standard_price_payment_onetime_money").amount
    Double mrp = item.get("out_standard_price_fix_payment_monthly_money").amount

    Double gm = (1 - ( otc*(1+r) + N*mrc*(1+r))/(otp*(1-r) + N*mrp*(1-r)))*100
    gm.round(0)
  }
}