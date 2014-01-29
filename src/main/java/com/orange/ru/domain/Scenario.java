package com.orange.ru.domain;

import com.orange.ru.domain.product.ProductItem;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import static com.orange.ru.core.Utils.compareObjects;
/**
 * Реализация Scenario - версии заказа. Реализация позволяет аккумулировать любой набор услуг.
 * User: Зайнуллин Радик
 * Date: 02.05.13
 */
@NamedQueries({
  @NamedQuery(name="Scenario.findAll", query = "select d from Scenario d"),
  @NamedQuery(name="Scenario.findById", query = "select d from Scenario d where d.id = ?1")
})
@Entity
@Table(name="scenario")
public class Scenario implements Serializable {
  @Id
  @SequenceGenerator(name="SCENARIO_SEQ", sequenceName="SCENARIO_SEQ", allocationSize = 1, initialValue= 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCENARIO_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Scenario(){
    productItems = new HashSet<ProductItem>();
    lastUpdateDate = new LocalDateTime();
  }
  // сценарий содержит набор ProductItem, каждый ProductItem есть обертка для
  // услуги + денежные начисления по этой услуге
  @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinTable(name="scenario_products",
      joinColumns={@JoinColumn(name="scenario_id")},
      inverseJoinColumns={@JoinColumn(name="product_item_id")})
  private Set<ProductItem> productItems;
  public Set<ProductItem> getProductItems() { return productItems; }
  public void setProductItems(Set<ProductItem> productItems) { this.productItems = productItems; }

  @Lob
  @Column(name="note")
  private String note;
  public String getNote() { return note; }
  public void setNote(String note) { this.note = note; }

  /* Удалять, менять сценарий оппортьюнити может только его владелец. */
  @Column(name="owner_email", nullable = true, updatable = true)
  private String ownerEmail;
  public String getOwnerEmail() { return ownerEmail; }
  public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }

  // срок контракта хранится в сценарии, один из параметров, которым играют менеджеры
  @Column(name="contract_term", nullable = true, insertable=false, updatable = true, columnDefinition = "int default 12")
  private Integer contractTerm;// Срок контракта - 12, 24, 36 месяцев
  public Integer getContractTerm() { return contractTerm; }
  public void setContractTerm(Integer contractTerm) { this.contractTerm = contractTerm; }

  @Column(name = "last_update_date", nullable = false)
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
  private LocalDateTime lastUpdateDate;
  public LocalDateTime getLastUpdateDate() { return lastUpdateDate; }
  public void setLastUpdateDate(LocalDateTime lastUpdateDate) { this.lastUpdateDate = lastUpdateDate; }

  // --- override Object ---
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    if (contractTerm != null && ownerEmail != null) result = 1*contractTerm*ownerEmail.hashCode();
    return prime * result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Scenario other = (Scenario) obj;
    if (id != null && other.getId() != null) return this.id.equals(other.getId());
    else return compareObjects(this, other);
  }
  @Override
  public String toString(){
    return "Scenario - " + productItems.toString();
  }
}