package com.orange.ru.swf.model;

import org.joda.money.BigMoney;
import java.io.Serializable;

public class CostAccessLinesForm1 implements Serializable {
  private BigMoney equipmentCostOrange;
  public BigMoney getEquipmentCostOrange() { return equipmentCostOrange; }
  public void setEquipmentCostOrange(String equipmentCostOrange) { this.equipmentCostOrange = parse(equipmentCostOrange); }

  private BigMoney monthlyPaymentOrange;
  public BigMoney getMonthlyPaymentOrange() { return monthlyPaymentOrange; }
  public void setMonthlyPaymentOrange(String monthlyPaymentOrange) { this.monthlyPaymentOrange = parse(monthlyPaymentOrange); }

  private BigMoney oneTimePaymentOrange;
  public BigMoney getOneTimePaymentOrange() { return oneTimePaymentOrange; }
  public void setOneTimePaymentOrange(String oneTimePaymentOrange) { this.oneTimePaymentOrange = parse(oneTimePaymentOrange); }

  private BigMoney equipmentCostLease;
  public BigMoney getEquipmentCostLease() {
    return equipmentCostLease;
  }
  public void setEquipmentCostLease(String equipmentCostLease) {
    this.equipmentCostLease = parse(equipmentCostLease);
  }

  private BigMoney parse(String in){
    String var = in.replaceAll("\\s*", "").replaceAll("\\.", "\\,");
    return BigMoney.parse("RUR " + Double.valueOf(var));
  }

  private BigMoney monthlyPaymentLease;
  public BigMoney getMonthlyPaymentLease() { return monthlyPaymentLease; }
  public void setMonthlyPaymentLease(String monthlyPaymentLease) { this.monthlyPaymentLease = parse(monthlyPaymentLease); }

  private BigMoney oneTimePaymentLease;
  public BigMoney getOneTimePaymentLease() { return oneTimePaymentLease; }
  public void setOneTimePaymentLease(String oneTimePaymentLease) { this.oneTimePaymentLease = parse(oneTimePaymentLease); }

  private BigMoney equipmentCostRadio;
  public BigMoney getEquipmentCostRadio() { return equipmentCostRadio; }
  public void setEquipmentCostRadio(String equipmentCostRadio) { this.equipmentCostRadio = parse(equipmentCostRadio); }

  private BigMoney monthlyPaymentRadio;
  public BigMoney getMonthlyPaymentRadio() { return monthlyPaymentRadio; }
  public void setMonthlyPaymentRadio(String monthlyPaymentRadio) { this.monthlyPaymentRadio = parse(monthlyPaymentRadio); }

  private BigMoney supportMonthlyPaymentRadio;
  public BigMoney getSupportMonthlyPaymentRadio() { return supportMonthlyPaymentRadio; }
  public void setSupportMonthlyPaymentRadio(String supportMonthlyPaymentRadio) { this.supportMonthlyPaymentRadio = parse(supportMonthlyPaymentRadio); }

  private BigMoney legalizationCostRadio;
  public BigMoney getLegalizationCostRadio() { return legalizationCostRadio; }
  public void setLegalizationCostRadio(String legalizationCostRadio) { this.legalizationCostRadio = parse(legalizationCostRadio); }

  private BigMoney mountingCostRadio;
  public BigMoney getMountingCostRadio() { return mountingCostRadio; }
  public void setMountingCostRadio(String mountingCostRadio) { this.mountingCostRadio = parse(mountingCostRadio); }
}
