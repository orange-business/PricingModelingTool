package com.orange.ru.operation.bvpn

import com.orange.ru.domain.product.ProductItem
import com.orange.ru.operation.bvpn.ref.RefMuxNumber
import com.orange.ru.operation.ref.RefRiskPercent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.Transactional

import com.orange.ru.mongodb.repositories.BvpnCostsRepository
import com.orange.ru.operation.bvpn.ref.RefTariffUbbAllColors_1MB
import org.joda.money.BigMoney
import com.orange.ru.mongodb.repositories.*
import com.orange.ru.operation.bvpn.ref.RefPaymentMonthlyMinFee2M_Ubb
import com.orange.ru.operation.bvpn.ref.RefCoeffTariffCalculationForSpeed
import com.orange.ru.operation.bvpn.ref.RefCostMonthlyDepreciationPort
import com.orange.ru.operation.bvpn.ref.RefCostMonthlyDepreciationRouter1kbSec
import com.orange.ru.operation.bvpn.ref.RefCostMonthlyPortSupportPercent
import com.orange.ru.operation.bvpn.ref.RefCostMonthlyTransmissionCost1kb
import com.orange.ru.operation.bvpn.ref.RefStandardPricePayment_2M_Monthly
import com.orange.ru.operation.bvpn.ref.RefUtilizationNumber

/**
 * Здесь единый расчет стандартной цены для ubb схемы тарификации.
 */
@Service("standard_price_ubb_operation")
@Repository
@Transactional(readOnly = true)
class UbbStandardPriceOperationImpl implements UbbStandardPriceOperation {
  @Autowired RefRiskPercent ref_risk_percent
  @Autowired RefTariffUbbAllColors_1MB ref_tariff_ubb_all_colors_1MB_money
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
  @Autowired BvpnCostsRepository bvpnCostsRepository
  @Autowired BvpnTariffsRepository bvpnTariffsRepository
  @Autowired CoefficientRepository coefficientRepository
  @Autowired RefPaymentMonthlyMinFee2M_Ubb ref_payment_monthly_min_fee_2M_ubb_money

  void call(ProductItem item) {
    ref_risk_percent.calculate item
    ref_tariff_ubb_all_colors_1MB_money.calculate item
    ref_coeff_tariff_calculation_for_speed_number.calculate item
    ref_cost_monthly_depreciation_router_1kb_sec_money.calculate item
    ref_cost_monthly_port_support_percent.calculate item
    ref_cost_monthly_depreciation_port_money.calculate item
    ref_standard_price_payment_2M_monthly_money.calculate item
    ref_cost_monthly_transmission_cost_1kb_money.calculate item
    ref_utilization_number.calculate item
    ref_mux_number.calculate item
    ref_payment_monthly_min_fee_2M_ubb_money.calculate item

    // "Поддержка HELP DESK количество человеко-часов работы (на порт)
    item.addDouble("ref_cost_monthly_helpdesk_support_port_hour", 0.39)

    // Кост за 1 Мбайт = («кост за 1 кб/сек в мес»/0,30899/1024)
    item.addMoney("out_cost_traffic_1MB_money", calc2(bvpnCostsRepository.getMoneyByProductItem(item)))

    // Стандартная цена (UBB)	Маржа на трафик (на дата 3)
    item.addDouble("out_standard_price_ubb_margin_data3_percent",
        ((double) (1 - item.get("out_cost_traffic_1MB_money").amount / item.get("ref_tariff_ubb_data3_1MB_money").amount) * 100).round(2))

    // Поддержка HELP DESK стоимость на порт ежемесячно
    item.addMoney("out_cost_monthly_helpdesk_support_port_money",
        hourRepository.getMoneyByCost("CostCS").multipliedBy(item.get("ref_cost_monthly_helpdesk_support_port_hour")))

    // Стандартная цена (UBB)	минимальная сумма счета
    item.addMoney("out_standard_price_ubb_payment_monthly_money", calc5(item))

    // Расчет ежемесячных затрат (костов) за порт
    item.addMoney("out_standard_cost_monthly_money", calc(item))

    // Стандартная цена, разовый платеж
    item.addMoney("out_standard_price_payment_onetime_money",
        installationRepository.getMoneyByItem(item, (int) item.getDouble("in_contract_term_months")))

    //Косты IP VPN	Затраты за порт (разово),
    // равняется суммарной трате на работу специалиста = количество часов * стоимость труда специалиста
    item.addMoney("out_cost_onetime_money", hourRepository.calculateByCostAndProductItemType("CostFO", item))

  }
  //
  def static Closure calc2 = { BigMoney money ->
    double factor = 0.30899*1024
    BigMoney.parse("RUR " + (money.amount/factor).round(4))
  }

  def static Closure calc5 = { item ->
    BigMoney money = item.get("ref_payment_monthly_min_fee_2M_ubb_money")
    money.multipliedBy(item.get("ref_coeff_tariff_calculation_for_speed_number")).rounded(-2, java.math.RoundingMode.UP)
  }

  def static Closure calc = { item ->
    /* Расчет MRC за порт IP VPN (UBB)
    mrc_vpn = (mrp_std/d3_price) x MRC_VPN_1MB + mrc_dep_rout_kb x Capacity + MRP_STD*coeff_ext_net_supp+ mrc_supp_hd+mrc_dep_access_port
    где MRP_STD  - стандартный ежемесячный платеж за порт IP VPN (минимальная сумма счета),
    (MRP_STD  - тариф за порт:МСС (ежемесячно))
    D3Price  - стандартная цена за 1 Мбайт трафика (класс сервиса – Data3), MRC_VPN_1MB - кост за 1 Мбайт. */
    // стандартный эжемесячный платеж за порт IP VPN (минимальная сумма счета)
    double mrp = item.get("out_standard_price_ubb_payment_monthly_money").amount
    //  ref_tariff_ubb_data3_1MB_money - стандартная цена за 1 mb траффика, класс сервиса - Data3
    double d3 = item.getMoney("ref_tariff_ubb_data3_1MB_money").amount
    // out_cost_traffic_1MB_money = MRC_VPN_1MB
    double MB = item.getMoney("out_cost_traffic_1MB_money").amount
    // ref_cost_monthly_depreciation_router_1kb_sec_money  =  MRCDepRoutKb
    double dep1 = item.getMoney("ref_cost_monthly_depreciation_router_1kb_sec_money").amount
    // ref_cost_monthly_port_support_percent = CoeffExtNetworkSup
    double k = item.get("ref_cost_monthly_port_support_ratio")
    // out_cost_monthly_helpdesk_support_port_money   = MRCSupportHD
    double sup = item.getMoney("out_cost_monthly_helpdesk_support_port_money").amount
    // ref_cost_monthly_depreciation_port_money = MRCDepAccessPort
    double dep2 = item.getMoney("ref_cost_monthly_depreciation_port_money").amount
    (MB*mrp/d3 + dep1*item.product.port.speed + sup + dep2 + mrp*k).round(2)
  }
}