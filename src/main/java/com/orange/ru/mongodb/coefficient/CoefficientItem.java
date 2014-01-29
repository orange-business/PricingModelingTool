package com.orange.ru.mongodb.coefficient;

import java.util.*;

public class CoefficientItem {
  public CoefficientItem(){ townNames = new ArrayList<String>(); }
  private List<String> townNames;
  public List<String> getTownNames() { return townNames; }
  public void setTownNames(List<String> townNames) { this.townNames = townNames; }

  private Double value;
  public Double getValue() { return value; }
  public void setValue(Double value) { this.value = value; }

  public boolean checkName(String name){
    for (String curr: townNames){
      if (curr.toLowerCase().contains(name.toLowerCase())) return true;
    }
    return false;
  }
}
