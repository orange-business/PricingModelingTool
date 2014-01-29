package com.orange.ru.domain.product.enums;

import java.util.*;

// Схема тарификации Fix, Ubb
public enum PortTarifficationScheme {
  FIX(100, "fix", "Fix"), UBB(200, "ubb", "Ubb");
  private Integer value; String code, description;
  private PortTarifficationScheme(Integer value, String code, String description) {
    this.value = value;
    this.code = code;
    this.description = description;
  }
  private static final Map<Integer,PortTarifficationScheme> lookup = new HashMap<Integer, PortTarifficationScheme>();
  static { for (PortTarifficationScheme item : PortTarifficationScheme.values()) lookup.put(item.getValue(), item); }
  public Integer getValue() { return value; }
  public String getCode() { return code; }
  public String getDescription(){ return description; }
  public static PortTarifficationScheme getKey(Integer value) { return lookup.get(value); }

  public static PortTarifficationScheme getByCode(String code){
    if ("fix".equalsIgnoreCase(code)) return FIX;
    else if ("ubb".equalsIgnoreCase(code)) return UBB;
    else return null;
  }
}