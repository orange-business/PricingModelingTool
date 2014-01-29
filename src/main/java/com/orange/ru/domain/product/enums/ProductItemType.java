package com.orange.ru.domain.product.enums;

import java.util.*;

public enum ProductItemType {
  NEW(100,"new","New"), UPGRADE(200,"upgrade","Upgrade"),
  DEGRADE(300,"degrade","Degrade"), PRICE_REVIEW(400,"price_review","Price review");
  private Integer value;
  private String code, description;
  private ProductItemType(Integer value, String code, String description) {
    this.value = value;
    this.code = code;
    this.description = description;
  }
  private static final Map<Integer, ProductItemType> lookup = new HashMap<Integer, ProductItemType>();
  static {
    for (ProductItemType item : ProductItemType.values()) lookup.put(item.getValue(), item);
  }
  public Integer getValue() { return value; }
  public String getCode() { return code; }
  public String getDescription(){ return description; }
  public static ProductItemType getKey(Integer value) {
    return lookup.get(value);
  }
  public static ProductItemType getByCode(String code){
    if ("new".equalsIgnoreCase(code)) return NEW;
    else if ("upgrade".equalsIgnoreCase(code)) return UPGRADE;
    else if ("degrade".equalsIgnoreCase(code)) return DEGRADE;
    else if ("price_review".equalsIgnoreCase(code)) return PRICE_REVIEW;
    else return null;
  }
}