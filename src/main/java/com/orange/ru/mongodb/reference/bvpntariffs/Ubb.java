package com.orange.ru.mongodb.reference.bvpntariffs;

/**
 *
 * User: Зайнуллин Радик
 * Date: 23.07.13
 */
public class Ubb {
  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }

  public Ubb(){
    mcc = new Mcc();
    colors = new Colors();
  }
  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  private Mcc mcc;
  public Mcc getMcc() { return mcc; }
  public void setMcc(Mcc mcc) { this.mcc = mcc; }

  private Colors colors;
  public Colors getColors() { return colors; }
  public void setColors(Colors colors) { this.colors = colors; }

  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof Ubb)) return false;

    Ubb in = (Ubb) obj;
    if (!in.getCaption().equals(this.caption)) return false;
    if (!in.getDescription().equals(this.description)) return false;
    if (!in.getMcc().equals(this.mcc)) return false;
    if (!in.getColors().equals(this.colors)) return false;

    return true;
  }
}