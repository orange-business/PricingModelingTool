package com.orange.ru.domain.product.enums;

import java.util.HashMap;
import java.util.Map;

// Уровень покрытия - local, domestic, okrug
public enum PortCoverage {
  DOMESTIC(100, "domestic", "Domestic"), OKRUG(200, "okrug", "Okrug"), LOCAL(300, "local", "Local");
  private Integer value; String code, description;
  private PortCoverage(Integer value, String code, String description) {
    this.value = value;
    this.code = code;
    this.description = description;
  }
  private static final Map<Integer, PortCoverage> lookup = new HashMap<Integer, PortCoverage>();
  static { for (PortCoverage item : PortCoverage.values()) lookup.put(item.getValue(), item); }
  public Integer getValue() { return value; }
  public String getCode() { return code; }
  public String getDescription(){ return description; }
  public static PortCoverage getKey(Integer value) { return lookup.get(value); }

  public static PortCoverage getByCode(String code){
    if ("domestic".equalsIgnoreCase(code)) return DOMESTIC;
    else if ("okrug".equalsIgnoreCase(code)) return OKRUG;
    else if ("local".equalsIgnoreCase(code)) return LOCAL;
    else return null;
  }
}