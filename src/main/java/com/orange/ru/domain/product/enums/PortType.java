package com.orange.ru.domain.product.enums;

import java.util.*;

// Тип порта platinum, gold, silver
public enum PortType {
  PLATINUM(100, "platinum", "Platinum"), GOLD(200, "gold", "Gold"), SILVER(300, "silver", "Silver");
  private Integer value; String code, description;
  private PortType(Integer value, String code, String description) {
    this.value = value;
    this.code = code;
    this.description = description;
  }
  private static final Map<Integer,PortType> lookup = new HashMap<Integer, PortType>();
  static { for (PortType item : PortType.values()) lookup.put(item.getValue(), item); }
  public Integer getValue() { return value; }
  public String getCode() { return code; }
  public String getDescription(){ return description; }
  public static PortType getKey(Integer value) { return lookup.get(value); }

  public static PortType getByCode(String code){
    if ("platinum".equalsIgnoreCase(code)) return PLATINUM;
    else if ("gold".equalsIgnoreCase(code)) return GOLD;
    else if ("silver".equalsIgnoreCase(code)) return SILVER;
    else return null;
  }
}