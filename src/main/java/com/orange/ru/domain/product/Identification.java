package com.orange.ru.domain.product;

import javax.persistence.*;
import java.io.Serializable;
import static com.orange.ru.core.Utils.compareObjects;
/**
 * Идентификация.
 * Для идентификационных кодов вводится понятие пространство имен.
 * Date: 26.06.13
 */
@NamedQueries({
  @NamedQuery(name="Identification.findByCode", query = "select c from Identification c where c.namespace =?1 and c.code = ?2"),
  @NamedQuery(name="Identification.findByNS", query = "select c from Identification c where c.namespace =?1 or c.namespace = 'common'"),
  @NamedQuery(name="Identification.findAll", query = "select c from Identification c")
})
@Entity
@Table(name = "identification")
public class Identification implements Serializable {
  @Id
  @SequenceGenerator(name="IDENTIFICATION_SEQ", sequenceName="IDENTIFICATION_SEQ", allocationSize = 1, initialValue= 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDENTIFICATION_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  //namespace + code -> unique = true
  @Column(name = "namespace", nullable = false)
  private String namespace;
  public String getNamespace() { return namespace; }
  public void setNamespace(String namespace) { this.namespace = namespace; }

  @Column(name = "code", nullable = false)
  private String code;
  public String getCode() { return code; }
  public void setCode(String name) { this.code = name; }

  @Column(name = "description", nullable = true)
  @Lob
  private String description;
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Column(name = "note", nullable = true)
  @Lob
  private String note;
  public String getNote() { return note; }
  public void setNote(String note) { this.note = note; }

  // --- override Object ---
  @Override
  public int hashCode() { return code.hashCode(); }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    Identification other = (Identification) obj;
    if (id != null && other.getId() != null) return this.id.equals(other.getId());
    else return compareObjects(this, other);
  }
  public boolean equalsByCode(String code) {  return this.code.equalsIgnoreCase(code); }
  @Override
  public String toString() { return namespace + ": " + code + ", " + description; }
}
