package com.orange.ru.domain.product;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Услуга
 * User: Зайнуллин Радик
 * Date: 04.04.13
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Product implements Serializable {
  public String getName() { return ""; }
  public String getCode() { return ""; }
  public static String getClassCode() { return ""; }

  public Product(){}
  @Id
  @SequenceGenerator(name="PRODUCT_SEQ", sequenceName="PRODUCT_SEQ", allocationSize = 1, initialValue= 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
}