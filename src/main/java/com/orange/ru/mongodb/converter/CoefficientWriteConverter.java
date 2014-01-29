package com.orange.ru.mongodb.converter;

import com.mongodb.*;
import com.orange.ru.mongodb.coefficient.Coefficient;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
/**
 * .
 * User: radik
 * Date: 01.08.13
 */
public class CoefficientWriteConverter implements Converter<Coefficient, DBObject> {
  @Override
  public DBObject convert(Coefficient src) {
    DBObject dbo = new BasicDBObject();
    dbo.put("caption", src.getCaption());
    dbo.put("description", src.getDescription());
    dbo.put("source", src.getSource());
//    ObjectId objId = new ObjectId(src.getId().toString(16));
//    dbo.put("_id", objId);
    DBObject speed = new BasicDBObject();
    speed.put("caption",src.getSpeed().getCaption());
    speed.put("value",src.getSpeed().getValue());
    speed.put("dimension",src.getSpeed().getDimension());
    dbo.put("speed", speed);
    BasicDBList coefficient = new BasicDBList();
    dbo.put("coefficient", coefficient);
    // moscow
    DBObject moscow = new BasicDBObject();
    coefficient.add(moscow);
    moscow.put("key", "moscow");
    moscow.put("value", src.getCoefficient().get("moscow").getValue());
    BasicDBList moscowNames = new BasicDBList();
    moscowNames.add("Москва");
    moscowNames.add("Moscow");
    moscow.put("names", moscowNames);
    // petersburg
    DBObject petersburg = new BasicDBObject();
    coefficient.add(petersburg);
    petersburg.put("key", "petersburg");
    petersburg.put("value", src.getCoefficient().get("petersburg").getValue());
    BasicDBList petersburgNames = new BasicDBList();
    petersburgNames.add("Петербург");
    petersburgNames.add("Petersburg");
    petersburg.put("names", petersburgNames);
    // other
    DBObject other = new BasicDBObject();
    coefficient.add(other);
    other.put("key", "other");
    other.put("value", src.getCoefficient().get("other").getValue());
    BasicDBList otherNames = new BasicDBList();
    otherNames.add("другие");
    otherNames.add("others");
    other.put("names", otherNames);

    return dbo;
  }
}
