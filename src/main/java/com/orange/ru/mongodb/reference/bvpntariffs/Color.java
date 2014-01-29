package com.orange.ru.mongodb.reference.bvpntariffs;

import org.joda.money.BigMoney;
import org.joda.money.Money;
/**
 * .
 * User: Зайнуллин Радик
 * Date: 23.07.13
 */
public class Color {
  public Color(){}
  public Color(String dimension, BigMoney value){
    this.dimension = dimension;
    this.value = value;
  }
  private String dimension;
  public String getDimension() { return dimension; }
  public void setDimension(String dimension) { this.dimension = dimension; }

  private BigMoney value;
  public BigMoney getValue() { return value; }
  public void setValue(BigMoney value) { this.value = value; }

  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof Color)) return false;

    Color in = (Color) obj;
    if (!in.getDimension().equals(this.dimension)) return false;
    if (!in.getValue().equals(this.value)) return false;

    return true;
  }
}