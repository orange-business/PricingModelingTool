package com.orange.ru.operation.bvpn

import com.orange.ru.domain.product.ProductItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import static java.math.RoundingMode.CEILING
/**
 Для FIX – проверка желаемой цены.
 Входные данные:
 Запрошенная цена (FIX) разовый платеж - in_required_price_fix_payment_onetime_money
 Запрошенная цена (FIX) ежемесячный платеж - in_required_price_fix_payment_monthly_money
 Посчитать:
 Стандартная цена, разовый платеж - out_standard_price_payment_onetime_money
 Стандартная цена (FIX), ежемесячный платеж - out_standard_price_fix_payment_monthly_money
 Косты IP VPN	Затраты за порт (разово), равняется суммарной трате на работу специалиста = количество часов * стоимость труда специалиста	out_cost_onetime_money
 Косты IP VPN	Затраты за порт (ежемесячно)	out_required_cost_monthly_money
 Запрошенная цена (FIX)	Маржа	out_required_price_fix_margin_percent

 Запрошенная цена (FIX)	Скидка от стандартной цены, разово out_required_price_fix_discount_onetime_percent
 Запрошенная цена (FIX)	Скидка от стандартной цены, ежемесячно	out_required_price_fix_discount_monthly_percent
 Формула маржи для VPN:

 Формула скидки для последней мили:

 скидка разовый:
 out_required_price_fix_discount_onetime_percent =
           1- in_required_price_fix_payment_onetime_money/out_standard_price_payment_onetime_money

 скидка ежемесячная:
 out_required_price_fix_discount_monthly_percent =
           1- in_required_price_fix_payment_monthly_money/out_standard_price_fix_payment_monthly_money

 Формулы расчет маржи ():
 GM = 1 - (OTC  x (1+ R) + N x MRC x (1+ R))/(OTP x (1-R) + N x MRP * (1-R))
 */
@Service("required_price_fix_operation")
@Repository
@Transactional(readOnly = true)
class FixRequiredPriceOperationImpl implements FixRequiredPriceOperation {
  @Autowired FixStandardPriceOperation standard_price_fix_operation

  @Override
  void call(ProductItem item) {
    standard_price_fix_operation.call item

    // Расчет ежемесячных затрат (костов) за порт
    item.addMoney("out_required_cost_monthly_money", calc_out_cost_monthly_money(item))

    // Запрошенная цена (FIX), Скидка от стандартной цены, разово
    item.addDouble("out_required_price_fix_discount_onetime_percent",
      (1- item.get("in_required_price_fix_payment_onetime_money").amount/item.get("out_standard_price_payment_onetime_money").amount)*100
    )
    // Запрошенная цена (FIX), Скидка от стандартной цены, ежемесячно
    item.addDouble("out_required_price_fix_discount_monthly_percent",
      (1- item.get("in_required_price_fix_payment_monthly_money").amount/item.get("out_standard_price_fix_payment_monthly_money").amount)*100
    )
    // Запрошенная цена (FIX)	Маржа
    item.addDouble("out_required_price_fix_margin_percent", calc(item))
  }
  def static Closure calc = { item ->
    double otc = item.get("out_cost_onetime_money").amount, r = item.get("ref_risk_ratio")
    double mrc = item.get("out_required_cost_monthly_money").amount, otp = item.get("in_required_price_fix_payment_onetime_money").amount
    double mrp = item.get("in_required_price_fix_payment_monthly_money").amount, n = item.get("in_contract_term_months")
    ((1 - (otc*(1+r)+n*mrc*(1+r))/(otp*(1-r)+n*mrp*(1-r)))*100).round(2)
  }
  def static Closure calc_out_cost_monthly_money = { item ->
    /* mrc_pe = mrc_pe_1Кbit* Capacity  + mrc_deprouter_Kbit* Capacity  + mrp_pe * CoeffExtNetworkSup + mrc_supportHD + mrc_depaccessport
       где mrc_pe - стандартные ежемесячный платеж за порт IP VPN, mrc_pe_1Кbit - кост IP VPN за 1 Кбит */
    (item.get("out_cost_monthly_transmission_cost_1kb_money").multipliedBy((Long) item.product.port.speed) +
        item.get("ref_cost_monthly_depreciation_router_1kb_sec_money").multipliedBy((Long) item.product.port.speed) +
        item.get("in_required_price_fix_payment_monthly_money").multipliedBy(item.get("ref_cost_monthly_port_support_ratio")) +
        item.get("out_cost_monthly_helpdesk_support_port_money") +
        item.get("ref_cost_monthly_depreciation_port_money")).rounded(2, CEILING)
  }

}