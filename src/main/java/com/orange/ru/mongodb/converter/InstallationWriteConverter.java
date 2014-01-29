package com.orange.ru.mongodb.converter;

import com.mongodb.*;
import org.bson.types.ObjectId;
import com.orange.ru.mongodb.reference.installation.Installation;
import org.springframework.core.convert.converter.Converter;
/**
 * Справочник.
 * User: Зайнуллин Радик
 * Date: 22.07.13
 */
public class InstallationWriteConverter implements Converter<Installation, DBObject> {
  @Override
  public DBObject convert(Installation src) {
    DBObject dbo = new BasicDBObject();
    ObjectId objId = new ObjectId(src.getId().toString(16));
    dbo.put("_id", objId);

    dbo.put("index", src.getIndex());

    DBObject agreement = new BasicDBObject();
    agreement.put("duration", src.getAgreement().getDuration());
    agreement.put("dimension", src.getAgreement().getDimension());
    agreement.put("caption", src.getAgreement().getCaption());
    dbo.put("agreement", agreement);

    DBObject velocity = new BasicDBObject();
    velocity.put("lower_bound", src.getVelocity().getLowerBound());
    velocity.put("upper_bound", src.getVelocity().getUpperBound());
    velocity.put("dimension", src.getVelocity().getDimension());
    velocity.put("caption", src.getVelocity().getCaption());
    dbo.put("velocity", velocity);

    DBObject payment = new BasicDBObject();
    payment.put("onetime", src.getPayment().getOnetime().getAmount());
    payment.put("currency", src.getPayment().getCurrency());
    payment.put("caption", src.getPayment().getCaption());
    dbo.put("payment", payment);

    DBObject grade = new BasicDBObject();
    grade.put("caption", src.getGrade().getCaption());
    grade.put("value", src.getGrade().getValue());
    dbo.put("grade", grade);
    return dbo;
  }
}