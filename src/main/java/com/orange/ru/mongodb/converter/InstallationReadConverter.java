package com.orange.ru.mongodb.converter;

import com.mongodb.DBObject;
import com.orange.ru.mongodb.reference.installation.*;
import org.joda.money.BigMoney;
import org.springframework.core.convert.converter.Converter;
import java.math.BigInteger;
import org.bson.types.ObjectId;
/**
 * Справочник.
 * User: Зайнуллин Радик
 * Date: 22.07.13
 */
public class InstallationReadConverter implements Converter<DBObject, Installation> {
  @Override
  public Installation convert(DBObject dbo) {
    DBObject obj = null;
    Installation installation = new Installation();
    ObjectId id = (ObjectId) dbo.get("_id");
    installation.setId(new BigInteger(id.toString(), 16));

    // index
    installation.setIndex((String) dbo.get("index"));

    // agreement
    Agreement agreement = new Agreement();
    obj = (DBObject) dbo.get("agreement");
    agreement.setCaption((String) obj.get("caption"));
    agreement.setDimension((String) obj.get("dimension"));
    agreement.setDuration((Integer) obj.get("duration"));
    installation.setAgreement(agreement);

    // velocity
    Velocity velocity = new Velocity();
    obj = (DBObject) dbo.get("velocity");
    velocity.setLowerBound((Integer) obj.get("lower_bound"));
    velocity.setUpperBound((Integer) obj.get("upper_bound"));
    velocity.setCaption((String) obj.get("caption"));
    velocity.setDimension((String) obj.get("dimension"));
    installation.setVelocity(velocity);

    // payment
    Payment payment = new Payment();
    obj = (DBObject) dbo.get("payment");
    payment.setCaption((String) obj.get("caption"));
    payment.setCurrency((String) obj.get("currency"));
    payment.setOnetime(BigMoney.parse("RUR " + (Double)obj.get("onetime")));
    installation.setPayment(payment);

    // grade
    Grade grade = new Grade();
    obj = (DBObject) dbo.get("grade");
    grade.setCaption((String) obj.get("caption"));
    grade.setValue((String) obj.get("value"));
    installation.setGrade(grade);

    return installation;
  }
}
