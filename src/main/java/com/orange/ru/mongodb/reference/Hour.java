package com.orange.ru.mongodb.reference;

import org.joda.money.BigMoney;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;
import java.io.Serializable;
import java.math.BigInteger;
/**
 * Справочник "Стоимость трудозатрат".
 * User: Зайнуллин Радик
 * Date: 03.07.13
 */
@Document(collection = "hour")
public class Hour implements Serializable {
  @Id
  private BigInteger id;
  public BigInteger getId() { return id; }
  public void setId(BigInteger id) { this.id = id; }

  @Field("group")
  private String group;
  public String getGroup() { return group; }
  public void setGroup(String group) { this.group = group; }

  @Field("cost")
  @Indexed(unique = true)
  private String cost;
  public String getCost() { return cost; }
  public void setCost(String cost) { this.cost = cost; }

  @Field("longName")
  private String longName;
  public String getLongName() { return longName; }
  public void setLongName(String longName) { this.longName = longName; }


  @Field("shortName")
  private String shortName;
  public String getShortName() { return shortName; }
  public void setShortName(String shortName) { this.shortName = shortName; }

  @Field("value")
  private BigMoney value;
  public BigMoney getValue() { return value; }
  public void setValue(BigMoney value) { this.value = value; }

  @Field("valueCaption")
  private String valueCaption;
  public String getValueCaption() { return valueCaption; }
  public void setValueCaption(String valueCaption) { this.valueCaption = valueCaption; }

  @Field("valueCurrency")
  private String valueCurrency;
  public String getValueCurrency() { return valueCurrency; }
  public void setValueCurrency(String valueCurrency) { this.valueCurrency = valueCurrency; }
}
