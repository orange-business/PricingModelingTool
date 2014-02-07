package com.orange.ru.domain;

import com.orange.ru.domain.product.enums.CustomerType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import static com.orange.ru.core.Utils.compareObjects;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
  @Id
  @SequenceGenerator(name="CUSTOMER_SEQ", sequenceName="CUSTOMER_SEQ", allocationSize = 1, initialValue= 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Customer(){
    sites = new HashSet<Site>();
    opportunities = new HashSet<Opportunity>();
  }
  @Lob
  @Column(name = "official", updatable = true)
  private String official;
  public void setOfficial(String official) { this.official = official; }
  public String getOfficial() { return official; }

  @Column(name = "external_id", nullable = true)
  private String externalId;
  public String getExternalId() { return externalId; }
  public void setExternalId(String externalId) { this.externalId = externalId; }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
  private Set<Site> sites;
  public Set<Site> getSites() { return sites; }
  public void setSites(Set<Site> sites) { this.sites = sites; }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
  private Set<Opportunity> opportunities;
  public Set<Opportunity> getOpportunities() { return opportunities; }
  public void setOpportunities(Set<Opportunity> opportunities) { this.opportunities = opportunities; }

  @Column(name = "type_id")
  private Integer typeId;
  public CustomerType getType(){ return CustomerType.getKey(this.typeId); }
  public void setType(CustomerType type){ this.typeId = type.getValue(); }

  // --- override Object ---
  @Override
  public int hashCode() {
    return 31 + opportunities.size()^2 + official.hashCode();
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    Customer other = (Customer) obj;
    if (id != null && other.getId() != null) return this.id.equals(other.getId());
    else return compareObjects(this, other);
  }
  @Override
  public String toString() { return "Customer(" + official + ")"; }
}