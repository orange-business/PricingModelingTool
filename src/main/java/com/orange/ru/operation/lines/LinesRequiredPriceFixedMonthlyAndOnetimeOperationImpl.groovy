package com.orange.ru.operation.lines

import com.orange.ru.domain.product.ProductItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.Transactional
/**
 * Расчет срока окупаемости и маржи, если заданы и желаемая ежемесячная цена и желаемая разовая цена.
 */
@Service("required_price_with_monthly_and_onetime_fixed_operation")
@Repository
@Transactional(readOnly = false)
class LinesRequiredPriceFixedMonthlyAndOnetimeOperationImpl implements LinesRequiredPriceFixedMonthlyAndOnetimeOperation {
  @Autowired LinesStandardPriceOperation standard_price_operation

  @Override
  void call(ProductItem item) {
    standard_price_operation.call item

    // Скидка от стандартной цены, разово
    item.addDouble("out_required_price_discount_onetime_percent",
      Math.round((1-item.get("inout_required_price_payment_onetime_money").amount/item.get("out_standard_price_payment_onetime_money").amount)*100)
    )
    // Скидка от стандартной цены, ежемесячно
    item.addDouble("out_required_price_discount_monthly_percent",
      Math.round((1-item.get("inout_required_price_payment_monthly_money").amount/item.get("out_standard_price_payment_monthly_money").amount)*100)
    )
    // Расчет срока окупаемости и маржи
    double mrp = item.get("inout_required_price_payment_monthly_money").amount // заданное значение!!
    double otp = item.get("inout_required_price_payment_onetime_money").amount // заданное значение!!
    double mrc = item.get("out_cost_monthly_money").amount, sga = item.get("ref_cost_executive_salary_ratio")
    double N = item.get("in_contract_term_months"), r = item.get("ref_risk_ratio")
    double am_eq = item.get("ref_depreciation_equipment_months"), am_b = item.get("ref_depreciation_build_months")
    double cx_eq = item.get("in_capex_equipment_money").amount, cx_b = item.get("in_capex_build_money").amount
    double otc_rent = item.get("in_cost_onetime_lease_money").amount
    double gm = 1 - (cx_eq * N / am_eq + cx_b * N / am_b + otc_rent * (1 + r) + N * mrc * (1 + r)) / (otp * (1 - r) + N * mrp * (1 - r))
    double pb = (cx_eq + cx_b + otc_rent * (1 + r) + otp * sga - otp * (1 - r)) / (mrp * (1 - r) - mrp * sga - mrc * (1 + r))

    // Маржа
    item.addDouble("out_required_price_margin_percent", (gm * 100).round(2))
    // Срок окупаемости
    item.addDouble("out_required_price_payback_months", pb.round(0))
  }
}