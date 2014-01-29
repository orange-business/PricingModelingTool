package com.orange.ru.domain.product.enums;

import java.util.*;

public enum LinesType { // build, lease
  BUILD(100,"build","Build"),LEASE(200,"lease","Lease");
  private Integer value; String code, description;

  private LinesType(Integer value, String code, String description) {
    this.value = value;
    this.code = code;
    this.description = description;
  }
  private static final Map<Integer, LinesType> lookup = new HashMap<Integer,LinesType>();
  static { for (LinesType item : LinesType.values()) lookup.put(item.getValue(), item); }
  public Integer getValue() { return value; }
  public String getCode(){ return code; }
  public String getDescription(){ return description; }
  public static LinesType getKey(Integer value) { return lookup.get(value); }

  public static LinesType getByCode(String code){
    if ("build".equalsIgnoreCase(code)) return BUILD;
    else if ("lease".equalsIgnoreCase(code)) return LEASE;
    else return null;
  }
}