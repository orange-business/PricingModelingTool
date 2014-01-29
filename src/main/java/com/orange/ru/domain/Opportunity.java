package com.orange.ru.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import static com.orange.ru.core.Utils.compareObjects;
/**
 * Сущность - Opportunity.
 * User: Зайнуллин Радик
 * Date: 04.04.13
 */
@NamedQueries({
  @NamedQuery(name="Opportunity.findAllByClient", query = "select c from Opportunity c where c.customer = ?1"),
  @NamedQuery(name="Opportunity.findAllScenarios", query = "select c.scenarios from Scenario d, Opportunity c where c=?1")
})
@Entity(name = "Opportunity")
@Table(name = "opportunity")
public class Opportunity implements Serializable {
  @Id
  @SequenceGenerator(name="OPPORTUNITY_SEQ", sequenceName="OPPORTUNITY_SEQ", allocationSize = 1, initialValue= 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPPORTUNITY_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  @Column(name="external_id",nullable = false)
  private String externalId;
  public String getExternalId() { return externalId; }
  public void setExternalId(String externalId) { this.externalId = externalId; }

  @Column(name = "note", nullable = true)
  @Lob
  private String note;
  public String getNote() { return note; }
  public void setNote(String note) { this.note = note; }

  @Column(name = "creation_date", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")
  @org.hibernate.annotations.Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
  private LocalDateTime creationDate;
  public LocalDateTime getCreationDate() { return creationDate; }
  public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

  public Opportunity(){
    scenarios = new HashSet<Scenario>();
    setCreationDate(new LocalDateTime());
  }
  @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;
  public Customer getCustomer() { return customer; }
  public void setCustomer(Customer customer) { this.customer = customer; }

  @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
  @JoinTable(name = "opportunity_scenario", joinColumns = @JoinColumn(name = "opportunity_id"),
      inverseJoinColumns = @JoinColumn(name = "scenario_id"))
  private Set<Scenario> scenarios;
  public Set<Scenario> getScenarios() { return scenarios; }

  @Column(name = "IS_CLOSED", updatable = true, nullable = false, columnDefinition = "char(1) default 'N'")
  @org.hibernate.annotations.Type(type="yes_no")
  private Boolean isClosed;
  public Boolean isClosed() { return isClosed; }
  public void setClosed(Boolean closed) { isClosed = closed; }

  // --- override Object ---
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    if (externalId != null) result = result * prime * externalId.hashCode();
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    Opportunity other = (Opportunity) obj;
    if (id != null && other.getId() != null) return this.id.equals(other.getId());
    else return compareObjects(this, other);
  }
  @Override
  public String toString(){ return "Opportunity - number = " + externalId; }
}