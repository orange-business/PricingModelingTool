package com.orange.ru.operation.bvpn

import org.springframework.stereotype.Service
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.domain.product.ProductItem

@Service("required_price_ubb_operation")
@Repository
@Transactional(readOnly = true)
class UbbRequiredPriceOperationImpl implements UbbRequiredPriceOperation {
  @Autowired UbbStandardPriceOperation standard_price_ubb_operation

  @Override
  void call(ProductItem item) {
    standard_price_ubb_operation.call item

    // Расчет ежемесячных затрат (костов) за порт
    item.addMoney("out_required_cost_monthly_money", calc(item))

    // Запрошенная цена (ubb), Скидка от стандартной цены, разово
    item.addDouble("out_required_price_ubb_discount_onetime_percent", calc2(item))
    // Запрошенная цена (ubb), Скидка от стандартной цены, ежемесячно
    item.addDouble("out_required_price_ubb_discount_monthly_percent",
      (1-item.get("in_required_price_ubb_payment_monthly_money").amount/item.get("out_standard_price_ubb_payment_monthly_money").amount)*100
    )
    // Запрошенная цена (ubb)	Маржа
    item.addDouble("out_required_price_ubb_margin_data3_percent", calc1(item))
  }
  def static Closure calc = { item ->
    /* Расчет MRC за порт IP VPN (UBB)
    mrc_vpn = (mrp_std/d3_price) x MRC_VPN_1MB + mrc_dep_rout_kb x Capacity + MRP_STD*coeff_ext_net_supp+ mrc_supp_hd+mrc_dep_access_port
    где MRP_STD  - стандартный ежемесячный платеж за порт IP VPN (минимальная сумма счета),
    (MRP_STD  - тариф за порт:МСС (ежемесячно))
    D3Price  - стандартная цена за 1 Мбайт трафика (класс сервиса – Data3), MRC_VPN_1MB - кост за 1 Мбайт. */
    // стандартный эжемесячный платеж за порт IP VPN (минимальная сумма счета)
    double mrp = item.get("in_required_price_ubb_payment_monthly_money").amount
    //  ref_tariff_ubb_data3_1MB_money - стандартная цена за 1 mb траффика, класс сервиса - Data3
    double d3 = item.getMoney("ref_tariff_ubb_data3_1MB_money").amount
    //   out_cost_traffic_1MB_money     = MRC_VPN_1MB
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

  def static Closure calc1 = { item ->
    // на будущее!!! 1- out_cost_traffic_1MB_money/in_required_tariff_ubb_data3_1MB_money
    // 1- out_cost_traffic_1MB_money/ref_tariff_ubb_data3_1MB_money
    double numerator = item.get("out_cost_traffic_1MB_money").amount
    double denominator = item.get("ref_tariff_ubb_data3_1MB_money").amount
    ((1- numerator/denominator)*100).round(2)
  }
  def static Closure calc2 = { item ->
    double numerator = item.get("in_required_price_ubb_payment_onetime_money").amount
    double denominator = item.get("out_standard_price_payment_onetime_money").amount
    // out_standard_price_payment_onetime_money > 0 тогда  все нормально
    // out_standard_price_payment_onetime_money =0 тогда
    // 1) in_required_price_ubb_payment_onetime_money = 0 тогда на выходе 0%
    // 2) in_required_price_ubb_payment_onetime_money > 0 тогда на выходе -100%
    if (denominator>0) (1- numerator/denominator)*100
    else if (denominator ==0 & numerator == 0) 0
    else if (denominator ==0 & numerator > 0) -100
  }
}