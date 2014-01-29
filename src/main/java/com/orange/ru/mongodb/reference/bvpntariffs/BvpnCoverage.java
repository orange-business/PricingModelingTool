package com.orange.ru.mongodb.reference.bvpntariffs;

/**
 * .
 * User: Зайнуллин Радик
 * Date: 23.07.13
 */
public class BvpnCoverage {
  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }

  public BvpnCoverage(){
    fix = new Fix();
    ubb = new Ubb();
  }

  private Fix fix;
  public Fix getFix() { return fix; }
  public void setFix(Fix fix) { this.fix = fix; }

  private Ubb ubb;
  public Ubb getUbb() { return ubb; }
  public void setUbb(Ubb ubb) { this.ubb = ubb; }

  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof BvpnCoverage)) return false;

    BvpnCoverage in = (BvpnCoverage) obj;
    if (!in.getCaption().equals(this.caption)) return false;
    if (!in.getDescription().equals(this.description)) return false;
    if (!in.getFix().equals(this.fix)) return false;
    if (!in.getUbb().equals(this.ubb)) return false;

    return true;
  }
}
