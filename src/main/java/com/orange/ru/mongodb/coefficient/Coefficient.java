package com.orange.ru.mongodb.coefficient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Справочник "Коэффициент для расчета ...".
 * User: Зайнуллин Радик
 * Date: 03.07.13
 */
@Document(collection = "Coefficients")
public class Coefficient {
  @Field("caption")
  private String caption;
  public String getCaption() { return caption; }
  public void setCaption(String caption) { this.caption = caption; }

  @Field("description")
  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Field("source")
  private String source;
  public String getSource() { return source; }
  public void setSource(String source) { this.source = source; }

  public Coefficient(){
    speed = new Speed();
    coefficient = new HashMap<>();
  }
  @Id
  private BigInteger id;
  public BigInteger getId() { return id; }
  public void setId(BigInteger id) { this.id = id; }

  @Field("speed")
  @Indexed(unique = true)
  private Speed speed;
  public Speed getSpeed() { return speed; }
  public void setSpeed(Speed speed) { this.speed = speed; }

  @Field("coefficient")
  private Map<String, CoefficientItem> coefficient;
  public Map<String, CoefficientItem> getCoefficient() { return coefficient; }
  public void setCoefficient(Map<String, CoefficientItem> coefficient) { this.coefficient = coefficient; }
}
