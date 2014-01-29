package com.orange.ru.operation.lines

import com.orange.ru.domain.product.ProductItem
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
/**
 * Здесь расчет ежемесячной желаемой цены, если задана желаемая разовая цена.
 */
@Service("required_price_with_onetime_fixed_operation")
@Repository
@Transactional(readOnly = false)
class LinesRequiredPriceFixedOnetimeOperationImpl implements LinesRequiredPriceFixedOnetimeOperation {
  @Autowired LinesStandardPriceOperation standard_price_operation

  @Override
  void call(ProductItem item) {
    standard_price_operation.call item
    // ежемесячный платеж
    item.addMoney("inout_required_price_payment_monthly_money",
      ((item.get("out_cost_capex_onetime_money")+item.get("in_cost_onetime_lease_money")).amount == 0)? notFit(item):fit(item)
    )
    // Скидка от стандартной цены, разово
    item.addDouble("out_required_price_discount_onetime_percent",
      Math.round((1-item.get("inout_required_price_payment_onetime_money").amount/item.get("out_standard_price_payment_onetime_money").amount)*100)
    )
    // Скидка от стандартной цены, ежемесячно
    item.addDouble("out_required_price_discount_monthly_percent",
      Math.round((1-item.get("inout_required_price_payment_monthly_money").amount/item.get("out_standard_price_payment_monthly_money").amount)*100)
    )
  }
  def static Closure fit = { item ->
    double otp = item.get("inout_required_price_payment_onetime_money").amount // заданное значение!!

    double mrc = item.get("out_cost_monthly_money").amount, ref_gm = item.get("ref_standard_required_price_margin_ratio")
    double N = item.get("in_contract_term_months"), r = item.get("ref_risk_ratio")
    double gm0 = item.get("ref_standard_required_price_margin_ratio"), pb0 = item.get("ref_standard_required_price_payback_months")
    double sga = item.get("ref_cost_executive_salary_ratio")
    double am_eq = item.get("ref_depreciation_equipment_months"), am_b = item.get("ref_depreciation_build_months")
    double cx_eq = item.get("in_capex_equipment_money").amount, cx_b = item.get("in_capex_build_money").amount
    double otc_rent = item.get("in_cost_onetime_lease_money").amount

    double gm, pb, /* входное значение mrp */ mrp = mrc/(1-ref_gm); int k = 10000

    while (k > 0.99) {
      mrp += k
      gm = 1 - (cx_eq * N / am_eq + cx_b * N / am_b + otc_rent * (1 + r) + N * mrc * (1 + r)) / (otp * (1 - r) + N * mrp * (1 - r))
      pb = (cx_eq + cx_b + otc_rent * (1 + r) + otp * sga - otp * (1 - r)) / (mrp * (1 - r) - mrp * sga - mrc * (1 + r))
      if (gm >= gm0 & pb <= pb0) { mrp -= k; k /= 10 }
    }
    item.addDouble("out_required_price_margin_percent", gm * 100)
    // pb должен быть меньше или равен справочному значению ref_standard_required_price_payback_months
    item.addDouble( (pb > pb0)? pb0: pb.round(0), "out_required_price_payback_months")

    mrp.round(-1)//-> inout_required_price_payment_monthly_money
  }
  def static Closure notFit = { item ->
    item.addMoney(0,"inout_required_price_payment_onetime_money")
    double mrc = item.get("out_cost_monthly_money").amount, r = item.get("ref_risk_ratio")
    double ref_gm = item.get("ref_standard_required_price_margin_ratio")
    mrc*(1+r)/((1-ref_gm)*(1-r))// -> inout_required_price_payment_monthly_money
  }
}