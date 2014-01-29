package com.orange.ru.swf.model;

import com.orange.ru.domain.product.enums.PortCoverage;
import com.orange.ru.domain.product.enums.PortTarifficationScheme;
import com.orange.ru.domain.product.enums.PortType;
import java.io.Serializable;
/**
 * Поддержка создания порта
 * User: Зайнуллин Радик
 * Date: 30.05.13
 */
public class PortForm implements Serializable {
  public PortType[] getTypeValues() { return new PortType[]{ PortType.PLATINUM, PortType.GOLD, PortType.SILVER }; }
  public PortTarifficationScheme[] getTarifficationPlans() {
    return new PortTarifficationScheme[]{ PortTarifficationScheme.FIX, PortTarifficationScheme.UBB };
  }

  private PortType selectedType;
  public PortType getSelectedType() { return selectedType; }
  public void setSelectedType(PortType selectedType) { this.selectedType = selectedType; }

  private Integer selectedSpeed;
  public Integer getSelectedSpeed() { return selectedSpeed; }
  public void setSelectedSpeed(Integer selectedSpeed) { this.selectedSpeed = selectedSpeed; }

  private PortTarifficationScheme selectedTarifficationPlan;
  public PortTarifficationScheme getSelectedTarifficationPlan() { return selectedTarifficationPlan; }
  public void setSelectedTarifficationPlan(PortTarifficationScheme selectedTarifficationPlan) {
    this.selectedTarifficationPlan = selectedTarifficationPlan;
  }

  private PortCoverage selectedCoverage;
  public PortCoverage getSelectedCoverage() { return selectedCoverage; }
  public void setSelectedCoverage(PortCoverage selectedCoverage) { this.selectedCoverage = selectedCoverage; }

  public PortCoverage[] getCoverageValues() {
    return new PortCoverage[]{ PortCoverage.DOMESTIC, PortCoverage.OKRUG, PortCoverage.LOCAL };
  }
}