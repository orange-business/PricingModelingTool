package com.orange.ru.mongodb.converter;

import com.mongodb.*;
import com.orange.ru.mongodb.reference.Hour;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
/**
 * Конвертер.
 * User: Зайнуллин Радик
 * Date: 11.07.13
 */
public class HourWriteConverter implements Converter<Hour, DBObject> {
  @Override
  public DBObject convert(Hour src) {
    DBObject dbo = new BasicDBObject();
    ObjectId objId = new ObjectId(src.getId().toString(16));
    dbo.put("_id", objId);
    dbo.put("longName", src.getLongName());
    dbo.put("shortName", src.getShortName());
    dbo.put("group", src.getGroup());
    dbo.put("cost", src.getCost());
    dbo.put("value", src.getValue());
    dbo.put("valueCaption", src.getValueCaption());
    dbo.put("valueCurrency", src.getValueCurrency());
    return dbo;
  }
}