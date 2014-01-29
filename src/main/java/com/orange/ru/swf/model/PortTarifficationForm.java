package com.orange.ru.swf.model;

import java.io.Serializable;

public class PortTarifficationForm implements Serializable {
  private String selectedTarifficationPlan;
  public String getSelectedTarifficationPlan() { return selectedTarifficationPlan; }
  public void setSelectedTarifficationPlan(String selectedTarifficationPlan) { this.selectedTarifficationPlan = selectedTarifficationPlan; }
}