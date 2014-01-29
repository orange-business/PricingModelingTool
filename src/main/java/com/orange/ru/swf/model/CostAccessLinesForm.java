package com.orange.ru.swf.model;

import org.joda.money.BigMoney;
import org.joda.money.Money;
import java.io.Serializable;

public class CostAccessLinesForm implements Serializable {
  private BigMoney equipmentCostOrange;
  private BigMoney monthlyPaymentOrange;
  private BigMoney oneTimePaymentOrange;
  private BigMoney equipmentCostLease;
  private BigMoney monthlyPaymentLease;
  private BigMoney oneTimePaymentLease;
  private BigMoney equipmentCostRadio;
  private BigMoney monthlyPaymentRadio;
  private BigMoney supportMonthlyPaymentRadio;
  private BigMoney legalizationCostRadio;
  private BigMoney mountingCostRadio;

  public BigMoney getEquipmentCostOrange() {
    return equipmentCostOrange;
  }

  public void setEquipmentCostOrange(BigMoney equipmentCostOrange) {
    this.equipmentCostOrange = equipmentCostOrange;
  }

  public BigMoney getMonthlyPaymentOrange() {
    return monthlyPaymentOrange;
  }

  public void setMonthlyPaymentOrange(BigMoney monthlyPaymentOrange) {
    this.monthlyPaymentOrange = monthlyPaymentOrange;
  }

  public BigMoney getOneTimePaymentOrange() {
    return oneTimePaymentOrange;
  }

  public void setOneTimePaymentOrange(BigMoney oneTimePaymentOrange) {
    this.oneTimePaymentOrange = oneTimePaymentOrange;
  }

  public BigMoney getEquipmentCostLease() {
    return equipmentCostLease;
  }

  public void setEquipmentCostLease(BigMoney equipmentCostLease) {
    this.equipmentCostLease = equipmentCostLease;
  }

  public BigMoney getMonthlyPaymentLease() {
    return monthlyPaymentLease;
  }

  public void setMonthlyPaymentLease(BigMoney monthlyPaymentLease) {
    this.monthlyPaymentLease = monthlyPaymentLease;
  }

  public BigMoney getOneTimePaymentLease() {
    return oneTimePaymentLease;
  }

  public void setOneTimePaymentLease(BigMoney oneTimePaymentLease) {
    this.oneTimePaymentLease = oneTimePaymentLease;
  }

  public BigMoney getEquipmentCostRadio() {
    return equipmentCostRadio;
  }

  public void setEquipmentCostRadio(BigMoney equipmentCostRadio) {
    this.equipmentCostRadio = equipmentCostRadio;
  }

  public BigMoney getMonthlyPaymentRadio() {
    return monthlyPaymentRadio;
  }

  public void setMonthlyPaymentRadio(BigMoney monthlyPaymentRadio) {
    this.monthlyPaymentRadio = monthlyPaymentRadio;
  }

  public BigMoney getSupportMonthlyPaymentRadio() {
    return supportMonthlyPaymentRadio;
  }

  public void setSupportMonthlyPaymentRadio(BigMoney supportMonthlyPaymentRadio) {
    this.supportMonthlyPaymentRadio = supportMonthlyPaymentRadio;
  }

  public BigMoney getLegalizationCostRadio() {
    return legalizationCostRadio;
  }

  public void setLegalizationCostRadio(BigMoney legalizationCostRadio) {
    this.legalizationCostRadio = legalizationCostRadio;
  }

  public BigMoney getMountingCostRadio() {
    return mountingCostRadio;
  }

  public void setMountingCostRadio(BigMoney mountingCostRadio) {
    this.mountingCostRadio = mountingCostRadio;
  }
}
