package com.orange.ru.mongodb.reference.installation;

/**
 * Элемент коллекции Installation.
 * User: Зайнуллин Радик
 * Date: 22.07.13
 */
public class Velocity {
  private Integer lowerBound;
  public Integer getLowerBound() { return lowerBound; }
  public void setLowerBound(Integer lowerBound) { this.lowerBound = lowerBound; }

  private Integer upperBound;
  public Integer getUpperBound() { return upperBound; }
  public void setUpperBound(Integer upperBound) { this.upperBound = upperBound; }

  private String dimension;
  public String getDimension() { return dimension; }
  public void setDimension(String dimension) { this.dimension = dimension; }

  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }
}
