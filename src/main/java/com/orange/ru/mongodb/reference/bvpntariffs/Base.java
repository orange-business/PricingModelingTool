package com.orange.ru.mongodb.reference.bvpntariffs;

/**
 *
 * User: Administrator
 * Date: 06.08.13
 */
public class Base {
  private Integer value;
  public Integer getValue() { return value; }
  public void setValue(Integer value) { this.value = value; }

  private String dimension;
  public String getDimension() { return dimension; }
  public void setDimension(String dimension) { this.dimension = dimension; }

  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }

  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
}