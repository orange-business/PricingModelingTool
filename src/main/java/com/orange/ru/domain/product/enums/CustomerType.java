package com.orange.ru.domain.product.enums;

import java.util.*;

// категория клиента - корпоративный, оператор, новый …
public enum CustomerType {
  CORPORATE(100,"corporate","Corporate"),OPERATOR(200,"operator","Operator"),NEW(300,"new","New");
  private Integer value; String code, description;
  private CustomerType(Integer value, String code, String description) {
    this.value = value;
    this.code = code;
    this.description = description;
  }
  private static final Map<Integer, CustomerType> lookup = new HashMap<Integer, CustomerType>();
  static { for (CustomerType item : CustomerType.values()) lookup.put(item.getValue(), item); }
  public Integer getValue() { return value; }
  public String getCode() { return code; }
  public String getDescription(){ return description; }
  public static CustomerType getKey(Integer value) { return lookup.get(value); }

  public static CustomerType getByCode(String code){
    if ("new".equalsIgnoreCase(code)) return NEW;
    else if ("corporate".equalsIgnoreCase(code)) return CORPORATE;
    else if ("operator".equalsIgnoreCase(code)) return OPERATOR;
    else return null;
  }
}