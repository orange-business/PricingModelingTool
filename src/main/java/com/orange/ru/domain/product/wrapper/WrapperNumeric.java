package com.orange.ru.domain.product.wrapper;

import com.orange.ru.domain.product.Identification;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static com.orange.ru.core.Utils.compareObjects;
/**
 * Это обертка для любого не-денежного объекта.
 * purpose - назначение объекта, значение фиксированное - берется из справочника.
 * User: Зайнуллин Радик
 * Date: 25.06.13
 */
@Entity
@Table(name="wrapper_numeric")
public class WrapperNumeric implements Serializable {
  public WrapperNumeric(BigDecimal value, Identification identification){
    this(value.doubleValue(),identification);
  }
  public WrapperNumeric(Double value, Identification identification){
    this.value = value;
    this.identification = identification;
  }
  public WrapperNumeric(){}
  @Id
  @SequenceGenerator(name="WRAPPER_NUMERIC_SEQ", sequenceName="WRAPPER_NUMERIC_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WRAPPER_NUMERIC_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  // количество чего-нибудь
  @Column(name = "value")
  private Double value;
  public Double getValue() { return value; }
  public void setValue(Double value) { this.value = value; }

  // идентификация - уже готовые объекты лежащие в базе
  @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
  @JoinColumn(name = "identification_id", nullable = false)
  private Identification identification;
  public Identification getIdentification() { return identification; }
  public void setIdentification(Identification identification) { this.identification = identification; }

  // --- override Object ---
  @Override
  public int hashCode() {
    return value.hashCode() + identification.hashCode();
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    WrapperNumeric other = (WrapperNumeric) obj;
    if (id != null && other.getId() != null) return this.id.equals(other.getId());
    else return compareObjects(this, other);
  }
  @Override
  public String toString(){
    return identification.getDescription() + " " + value;
  }
}