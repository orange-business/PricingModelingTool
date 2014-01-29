package com.orange.ru.mongodb.reference.installation;

/**
 * Элемент коллекции Installation.
 * User: Зайнуллин Радик
 * Date: 22.07.13
 */
public class Agreement {
  private Integer duration;
  public Integer getDuration() { return duration; }
  public void setDuration(Integer duration) { this.duration = duration; }

  public String getDimension() { return dimension; }
  public void setDimension(String dimension) { this.dimension = dimension; }
  private String dimension;

  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }
}
