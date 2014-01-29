package com.orange.ru.mongodb.reference.bvpntariffs;

import org.joda.money.BigMoney;

public class PortTyped {
  public PortTyped(){}
  public PortTyped(String name, BigMoney value, String dimension){
    this.name = name;
    this.value = value;
    this.dimension = dimension;
  }

  private String name;
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  private BigMoney value;
  public BigMoney getValue() { return value; }
  public void setValue(BigMoney value) { this.value = value; }

  private String dimension;
  public String getDimension() { return dimension; }
  public void setDimension(String dimension) { this.dimension = dimension; }

  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof PortTyped)) return false;

    PortTyped in = (PortTyped) obj;
    if (!in.getDimension().equals(this.dimension)) return false;
    if (!in.getName().equals(this.name)) return false;
    if (!in.getValue().equals(this.value)) return false;

    return true;
  }
}