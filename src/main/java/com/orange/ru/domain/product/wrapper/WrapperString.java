package com.orange.ru.domain.product.wrapper;

import com.orange.ru.domain.product.Identification;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import static com.orange.ru.core.Utils.compareObjects;
/**
 * Это обертка для строчного значения.
 * User: Зайнуллин Радик
 * Date: 25.06.13
 */
@Entity
@Table(name="wrapper_string")
public class WrapperString implements Serializable {
  public WrapperString(String value, Identification identification){
    this.value = value;
    this.identification = identification;
  }
  public WrapperString(){}
  @Id
  @SequenceGenerator(name="WRAPPER_STRING_SEQ", sequenceName="WRAPPER_STRING_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WRAPPER_STRING_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  // строковое значение чего нибудь
  @Column(name = "value")
  private String value;
  public String getValue() { return value; }
  public void setValue(String value) { this.value = value; }

  // идентификация - уже готовые объекты лежащие в базе
  @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
  @JoinColumn(name = "identification_id", nullable = false)
  private Identification identification;
  public Identification getIdentification() { return identification; }
  public void setIdentification(Identification identification) { this.identification = identification; }

  // --- override Object ---
  @Override
  public int hashCode() {
    LocalDateTime time = new LocalDateTime();
    return value.hashCode() + identification.hashCode() + time.hashCode();
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
  public String toString(){  return identification.getDescription() + " " + value; }
}