package com.orange.ru.mongodb.reference.bvpntariffs;

/**
 * .
 * User: Зайнуллин Радик
 * Date: 23.07.13
 */
public class Mcc {
  public Mcc(){
    silver = new PortTyped();
    gold = new PortTyped();
    platinum = new PortTyped();
  }
  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }

  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  private PortTyped silver, gold, platinum;
  public PortTyped getSilver() { return silver; }
  public void setSilver(PortTyped silver) { this.silver = silver; }
  public PortTyped getGold() { return gold; }
  public void setGold(PortTyped gold) { this.gold = gold; }
  public PortTyped getPlatinum() { return platinum; }
  public void setPlatinum(PortTyped platinum) { this.platinum = platinum; }

  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof  Mcc)) return false;

    Mcc in = ( Mcc) obj;
    if (!in.getCaption().equals(this.caption)) return false;
    if (!in.getDescription().equals(this.description)) return false;
    if (!in.getSilver().equals(this.silver)) return false;
    if (!in.getGold().equals(this.gold)) return false;
    if (!in.getPlatinum().equals(this.platinum)) return false;

    return true;
  }
}