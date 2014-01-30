package com.orange.ru.domain.product;

import com.orange.ru.domain.product.comparator.*;
import com.orange.ru.domain.product.enums.ProductItemType;
import com.orange.ru.domain.product.wrapper.*;
import org.joda.money.BigMoney;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
/**
 * Обертка для услуги. Здесь есть ссылка на сконфигурированную услугу,
 * набор всех платежей, относящихся к этой сконфигурированной услуге.
 * User: Зайнуллин Радик
 * Date: 04.04.13
 */
@Entity
@Table(name = "product_item")
public class ProductItem implements Serializable {
  @Id
  @SequenceGenerator(name="PRODUCT_ITEM_SEQ", sequenceName="PRODUCT_ITEM_SEQ", allocationSize = 1, initialValue= 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ITEM_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  @Transient
  private Map<String, Identification> identificationMap;
  public Map<String, Identification> getIdentificationMap() { return identificationMap; }
  public void setIdentificationMap(Map<String, Identification> identificationMap) { this.identificationMap = identificationMap; }

  @Column(name = "type_id")
  private Integer typeId;
  public ProductItemType getType(){ return ProductItemType.getKey(this.typeId); }
  public void setType(ProductItemType type){ this.typeId = type.getValue(); }

  public ProductItem(){}
  public ProductItem(Product product){
    this.treasures = new TreeSet(new MoneyComparator());
    this.numerics =  new TreeSet(new NumericComparator());
    this.strings =  new TreeSet(new StringComparator());
    this.product = product;
  }
  /* Обертываемый Product, либо AccessLines, либо Business VPN */
  @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", nullable = true)
  private Product product;
  public Product getProduct() { return product; }
  public void setProduct(Product product) { this.product = product; }

  // набор всех денежных начислений, которые относятся к услуге (Product)
  @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
  @JoinTable(name = "wrapper_string_parent",
      joinColumns = @JoinColumn(name = "parent_id"), // это id самого product item
      inverseJoinColumns = @JoinColumn(name = "wrapper_string_id")) // это id строкового параметра
  private Set<WrapperString> strings;
  public Set<WrapperString> getStrings() { return strings; }
  public void setStrings(Set<WrapperString> strings) { this.strings = strings; }

  public void addString(String code, String str){
    WrapperString wrapper = new WrapperString(str, identificationMap.get(code));
    strings.remove(wrapper);
    strings.add(wrapper);
  }
  public String getString(String code){
    for (WrapperString str : strings){
      if (str.getIdentification().equalsByCode(code)) return str.getValue();
    }
    return null;
  }
  // набор всех денежных начислений, которые относятся к услуге (Product)
  @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
  @JoinTable(name = "wrapper_money_parent",
      joinColumns = @JoinColumn(name = "parent_id"),
      inverseJoinColumns = @JoinColumn(name = "wrapper_money_id"))
  private Set<WrapperMoney> treasures;
  public Set<WrapperMoney> getTreasures() { return treasures; }
  public void setTreasures(Set<WrapperMoney> treasures) { this.treasures = treasures; }

  // набор всех денежных начислений, которые относятся к услуге (Product)
  // parent_id - это ссылка на ту
  @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
  @JoinTable(name = "wrapper_numeric_parent",
      joinColumns = @JoinColumn(name = "parent_id"),
      inverseJoinColumns = @JoinColumn(name = "wrapper_numeric_id"))
  private Set<WrapperNumeric> numerics;
  public Set<WrapperNumeric> getNumerics() { return numerics; }
  public void setNumerics(Set<WrapperNumeric> numerics) { this.numerics = numerics; }

  public void addDouble(String code, Double value){
    WrapperNumeric wrapper = new WrapperNumeric(value,identificationMap.get(code));
    numerics.remove(wrapper);
    numerics.add(wrapper);
  }
  public Double getDouble(String code){
    for (WrapperNumeric numeric: numerics){
      if (numeric.getIdentification().equalsByCode(code)) return numeric.getValue();
    }
    return null;
  }
  public BigMoney getMoney(String code){
    for (WrapperMoney treasure: treasures){
      if (treasure.getIdentification().equalsByCode(code)) return treasure.getMoney();
    }
    return null;
  }
  public Object get(String code){
    if (code.endsWith("money")) return getMoney(code);
    if (code.endsWith("string"))return getString(code);
    if (code.endsWith("percent")||code.endsWith("number")||code.endsWith("months")) return getDouble(code);
    if (code.endsWith("ratio")) return getDouble(code.replace("ratio","percent"))/100;
    if (code.endsWith("hour")) return getDouble(code);
    return null;
  }
  public void addMoney(String code, Double num){
    addMoney(code, BigMoney.parse("RUR "+num));
  }
  public void addMoney(String code, BigMoney money){
    WrapperMoney wrapper = new WrapperMoney(money, identificationMap.get(code));
    treasures.remove(wrapper);
    treasures.add(wrapper);
  }

  @Lob
  @Column(name = "note", nullable = true)
  private String note;
  public String getNote() { return note; }
  public void setNote(String note) { this.note = note; }

  // --- override Object ---
  @Override
  public int hashCode() {
    Integer res = 1;
    if (treasures != null) {
      for (WrapperMoney treasure: treasures) res = res + treasure.hashCode();
    }
    return res + product.hashCode();
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    else return false; // if (obj == null || getClass() != obj.getClass()) return false;
  }
  @Override
  public String toString(){
    return "ProductItem: " + product.toString() + ", treasures = " + treasures.size();
  }
}