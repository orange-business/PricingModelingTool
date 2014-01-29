package com.orange.ru.operation.lines

import com.orange.ru.domain.product.ProductItem
import org.springframework.stereotype.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import com.orange.ru.operation.ref.RefRiskPercent
import com.orange.ru.operation.lines.ref.*
/**
 * Здесь единый расчет стандартной цены.
 */
@Service("standard_price_operation")
@Repository
@Transactional(readOnly = false)
class LinesStandardPriceOperationImpl implements LinesStandardPriceOperation{
  @Autowired RefRiskPercent ref_risk_percent
  @Autowired RefCoefficientOnetimePaymentPercent ref_coefficient_onetime_payment_percent
  @Autowired RefCostExecutiveSalaryPercent ref_cost_executive_salary_percent
  @Autowired RefDepreciationEquipmentMonths ref_depreciation_equipment_months
  @Autowired RefDepreciationBuildMonths ref_depreciation_build_months
  @Autowired RefStandardRequiredPriceMarginPercent ref_standard_required_price_margin_percent
  @Autowired RefStandardRequiredPricePaybackMonths ref_standard_required_price_payback_months

  void call(ProductItem item){
    ref_risk_percent.calculate item; ref_coefficient_onetime_payment_percent.calculate item; ref_depreciation_build_months.calculate item
    ref_cost_executive_salary_percent.calculate item; ref_depreciation_equipment_months.calculate item
    ref_standard_required_price_margin_percent.calculate item; ref_standard_required_price_payback_months.calculate item

    // Суммарные ежем. затраты = Ежем. затраты на аренду канала + Ежем. затраты на строительство последней мили
    item.addMoney("out_cost_monthly_money", item.get("in_cost_monthly_lease_money") + item.get("in_cost_monthly_build_money"))

    // Суммарные кап. затраты = Кап. затраты на оборудование + Кап. затраты на строительство
    item.addMoney("out_cost_capex_onetime_money",item.get("in_capex_equipment_money") + item.get("in_capex_build_money"))

    // Стандартная цена, разовый платеж
    item.addMoney("out_standard_price_payment_onetime_money",
       (item.get("out_cost_capex_onetime_money")+item.get("in_cost_onetime_lease_money")==0)?
         0: (item.get("out_cost_capex_onetime_money")+item.get("in_cost_onetime_lease_money")).multipliedBy(item.get("ref_coefficient_onetime_payment_ratio"))
    )
    // Расчет подбором для стандартной цены:
    item.addMoney("out_standard_price_payment_monthly_money",
      (item.get("out_cost_capex_onetime_money")+item.get("in_cost_onetime_lease_money")==0)? fit(item):notFit(item)
    )
  }
  def static Closure fit = { item ->
    item.getMoney("out_cost_monthly_money").multipliedBy((1+item.get("ref_risk_ratio"))/((1-item.get("ref_standard_required_price_margin_ratio"))*(1-item.get("ref_risk_ratio")))).amount
  }
  def static Closure notFit = { item ->
    double N = item.get("in_contract_term_months"), r = item.get("ref_risk_ratio")
    double gm0 = item.get("ref_standard_required_price_margin_ratio"), pb0 = item.get("ref_standard_required_price_payback_months")
    double sga = item.get("ref_cost_executive_salary_ratio")
    double am_eq = item.get("ref_depreciation_equipment_months"), am_b = item.get("ref_depreciation_build_months")

    double mrc = item.get("out_cost_monthly_money").amount
    double cx_eq = item.get("in_capex_equipment_money").amount, cx_b = item.get("in_capex_build_money").amount
    double otc_rent = item.get("in_cost_onetime_lease_money").amount
    double otp = item.get("out_standard_price_payment_onetime_money").amount

    double gm, pb, /* начальное значение */ mrp = item.get("out_cost_monthly_money").amount; int k = 10000

    while (k > 0.99) {
      mrp += k
      gm = 1 - (cx_eq * N / am_eq + cx_b * N / am_b + otc_rent * (1 + r) + N * mrc * (1 + r)) / (otp * (1 - r) + N * mrp * (1 - r))
      pb = (cx_eq + cx_b + otc_rent * (1 + r) + otp * sga - otp * (1 - r)) / (mrp * (1 - r) - mrp * sga - mrc * (1 + r))
      if (gm >= gm0 & pb <= pb0) { mrp -= k; k /= 10 }
    }
    item.addDouble("out_standard_price_margin_percent", gm.round(4) * 100)
    // pb должен быть меньше или равен справочному значению ref_standard_required_price_payback_months
    item.addDouble("out_standard_price_payback_months", (pb > pb0)? pb0: pb.round(0))
    mrp.round(-1) // -> out_standard_price_payment_monthly_money
  }
}