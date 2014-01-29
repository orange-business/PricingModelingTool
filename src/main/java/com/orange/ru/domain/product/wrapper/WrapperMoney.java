package com.orange.ru.domain.product.wrapper;

import com.orange.ru.domain.product.Identification;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.BigMoney;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static com.orange.ru.core.Utils.compareObjects;
/**
 * Это обертка для любого денежного объекта.
 * purpose - назначение денег, значение фиксированное - берется из справочника.
 * User: Зайнуллин Радик
 * Date: 25.06.13
 */
@Entity
@Table(name="wrapper_money")
public class WrapperMoney implements Serializable {
  public WrapperMoney(BigMoney money, Identification identification){
    this.money = money;
    this.identification = identification;
  }
  public WrapperMoney(BigDecimal amount, Identification identification){
    this(BigMoney.parse("RUR " + amount), identification);
  }
  public WrapperMoney(int amount, Identification identification) {
    this(BigMoney.parse("RUR " + amount), identification);
  }
  public WrapperMoney(double amount, Identification identification) {
    this(BigMoney.parse("RUR " + amount), identification);
  }

  public WrapperMoney(){}
  @Id
  @SequenceGenerator(name="WRAPPER_MONEY_SEQ", sequenceName="WRAPPER_MONEY_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WRAPPER_MONEY_SEQ")
  private Long id;
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  // количество денег
  @Type(type = "com.orange.ru.core.usertype.MoneyUserType")
  @Columns(columns = {@Column(name = "amount"), @Column(name = "currency")})
  private org.joda.money.BigMoney money;
  public BigMoney getMoney() { return money; }
  public void setMoney(BigMoney money) { this.money = money; }

  // идентификация - уже готовые объекты лежащие в базе
  @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
  @JoinColumn(name = "identification_id", nullable = false)
  private Identification identification;
  public Identification getIdentification() { return identification; }
  public void setIdentification(Identification identification) { this.identification = identification; }

  // --- override Object ---
  @Override
  public int hashCode() {
    return money.hashCode() + identification.hashCode();
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;

    WrapperMoney other = (WrapperMoney) obj;
    if (id != null && other.getId() != null) return this.id.equals(other.getId());
    else return compareObjects(this, other);
  }
  @Override
  public String toString(){
    return identification.getDescription() + " " + money.getAmount() + " " + money.getCurrencyUnit();
  }
}
