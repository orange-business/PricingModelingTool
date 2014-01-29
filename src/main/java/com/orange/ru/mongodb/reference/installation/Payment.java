package com.orange.ru.mongodb.reference.installation;

import org.joda.money.BigMoney;

/**
 * Элемент коллекции Installation.
 * User: Зайнуллин Радик
 * Date: 22.07.13
 */
public class Payment {
  private BigMoney onetime;
  public BigMoney getOnetime() { return onetime; }
  public void setOnetime(BigMoney onetime) { this.onetime = onetime; }

  private String currency;
  public String getCurrency() { return currency; }
  public void setCurrency(String currency) { this.currency = currency; }

  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }
}
