package com.orange.ru.mongodb.reference.bvpncosts;

import org.joda.money.BigMoney;
import org.joda.money.Money;

import java.io.Serializable;

public class CostChannel implements Serializable {
  private String name;
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  private BigMoney value;
  public BigMoney getValue() { return value; }
  public void setValue(BigMoney value) { this.value = value; }

  private String dimension;
  public String getDimension() { return dimension; }
  public void setDimension(String dimension) { this.dimension = dimension; }

  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
}