package com.orange.ru.mongodb.reference.installation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Справочник "Стоимость инсталляции".
 * User: Зайнуллин Радик
 * Date: 03.07.13
 */
@Document(collection = "installation")
public class Installation implements Serializable {
  @Id
  private BigInteger id;
  public BigInteger getId() { return id; }
  public void setId(BigInteger id) { this.id = id; }

  @Field("agreement")
  private Agreement agreement;
  public Agreement getAgreement() { return agreement; }
  public void setAgreement(Agreement agreement) { this.agreement = agreement; }

  @Field("velocity")
  private Velocity velocity;
  public Velocity getVelocity() { return velocity; }
  public void setVelocity(Velocity velocity) { this.velocity = velocity; }

  @Field("payment")
  private Payment payment;
  public Payment getPayment() { return payment; }
  public void setPayment(Payment payment) { this.payment = payment; }

  @Field("grade")
  private Grade grade;
  public Grade getGrade() { return grade; }
  public void setGrade(Grade grade) { this.grade = grade; }

  @Field("index")
  @Indexed(unique = true)
  private String index;
  public String getIndex() { return index; }
  public void setIndex(String index) { this.index = index; }

  @Transient
  public static String key(Integer months, Integer speed){
         if (speed <= 2000)   return months + ".2MBit";
    else if (speed <= 10000)  return months + ".10MBit";
    else if (speed <= 100000) return months + ".100MBit";
    else return months + ".over.100MBit";
  }
}
