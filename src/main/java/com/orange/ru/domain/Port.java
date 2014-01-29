package com.orange.ru.domain;

import com.orange.ru.domain.product.enums.PortCoverage;
import com.orange.ru.domain.product.enums.PortTarifficationScheme;
import com.orange.ru.domain.product.enums.PortType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import static com.orange.ru.core.Utils.compareObjects;

@Entity
@Table(name="port")
public class Port implements Serializable {
  @Id
  @SequenceGenerator(name="PORT_SEQ", sequenceName="PORT_SEQ", allocationSize = 1, initialValue= 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PORT_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  @Column(name="speed", nullable = false, columnDefinition = "int default 64")
  private Integer speed;
  public Integer getSpeed() { return speed; }
  public void setSpeed(Integer speed) { this.speed = speed; }

  @Column(name="tariffication_scheme_id", nullable = false)
  private Integer tarifficationSchemeId;
  public PortTarifficationScheme getTarifficationScheme(){ return PortTarifficationScheme.getKey(this.tarifficationSchemeId); }
  public void setTarifficationScheme(PortTarifficationScheme tarifficationScheme){
    this.tarifficationSchemeId = tarifficationScheme.getValue();
  }

  @Column(name = "coverage_id")//доместик (Россия),округ (в пределах региона), локал (в пределах населенного пункта)
  private Integer coverageId;
  public PortCoverage getCoverage() { return PortCoverage.getKey(this.coverageId); }
  public void setCoverage(PortCoverage coverage) { this.coverageId = coverage.getValue(); }

  // Тип порта platinum, gold, silver
  @Column(name = "type_id")
  private Integer typeId;
  public PortType getType(){ return PortType.getKey(this.typeId); }
  public void setType(PortType type){ this.typeId = type.getValue(); }

  // --- override Object ---
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    if (coverageId != null) result = result * prime * coverageId^2;
    if (speed != null ) result = Math.abs(result - speed);
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    Port other = (Port) obj;
    if (id != null && other.getId() != null) return this.id.equals(other.getId());
    else return compareObjects(this, other);
  }
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder("Port: ");
    if (typeId != null) sb.append(getType().getDescription());
    if (coverageId != null) sb.append(getCoverage().getDescription());
    if (speed != null) sb.append(" Speed: " + speed);
    if (tarifficationSchemeId != null) sb.append(getTarifficationScheme());
    return sb.toString();
  }
}