package com.orange.ru.domain;

import javax.persistence.*;
import java.io.Serializable;
import static com.orange.ru.core.Utils.compareObjects;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * Entity Сайт. сайт - адрес где клиент располагает оборудованием, с нашей стороны это точка подключения.
 * User: Зайнуллин Радик
 */
@Entity
@Table(name="site")
public class Site implements Serializable {
  @Id
  @SequenceGenerator(name="SITE_SEQ", sequenceName="SITE_SEQ", allocationSize = 1, initialValue= 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SITE_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Site(){
    this.isLastMile = false;
    this.isBusinessCentre = false;
  }
  @Column(name="is_last_mile", nullable = false, updatable = true, columnDefinition = "char(1) default 'N'")
  @Type(type="yes_no")
  private Boolean isLastMile;
  public Boolean isLastMile() { return isLastMile; }
  public void setLastMile(Boolean lastMile) { isLastMile = lastMile; }

  @Column(name="is_business_centre", nullable = false, updatable = true, columnDefinition = "char(1) default 'N'")
  @Type(type="yes_no")
  private Boolean isBusinessCentre;
  public Boolean isBusinessCentre() { return isBusinessCentre; }
  public void setBusinessCentre(Boolean businessCentre) { isBusinessCentre = businessCentre; }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false, updatable = true)
  private Customer customer;
  public Customer getCustomer() { return customer; }
  public void setCustomer(Customer customer) { this.customer = customer; }

  @Column(name="address", nullable = false, updatable = true)
  @Lob
  private String address;
  public String getAddress() { return address; }
  public void setAddress(String name) { this.address = name; }

  // --- override Object ---
  @Override
  public int hashCode() {
    LocalDateTime time = new LocalDateTime();
    return address.hashCode() + time.hashCode();
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    Site other = (Site) obj;
    if (id != null && other.getId() != null) return new EqualsBuilder().append(this.id,other.getId()).isEquals();
    else return compareObjects(this, other);
  }
  @Override
  public String toString(){ return "Site - address = " + address; }
}