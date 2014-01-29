package com.orange.ru.swf.model;

import java.io.Serializable;

public class ProductGetConfiguredForm implements Serializable {
  private String selectedProductName;
  public String getSelectedProductName() { return selectedProductName; }
  public void setSelectedProductName(String selectedProductName) { this.selectedProductName = selectedProductName; }
}
