package com.orange.ru.mongodb.converter;

import com.mongodb.DBObject;
import com.orange.ru.mongodb.reference.Hour;
import org.bson.types.ObjectId;
import org.joda.money.BigMoney;
import org.springframework.core.convert.converter.Converter;
import java.math.BigInteger;
/**
 * Конвертер.
 * User: Зайнуллин Радик
 * Date: 12.07.13
 */
public class HourReadConverter implements Converter<DBObject, Hour> {
  @Override
  public Hour convert(DBObject dbo) {
    Hour res = new Hour();
    ObjectId id = (ObjectId) dbo.get("_id");
    res.setId(new BigInteger(id.toString(), 16));
    res.setCost((String) dbo.get("cost"));
    res.setGroup((String) dbo.get("group"));
    res.setLongName((String) dbo.get("longName"));
    res.setShortName((String) dbo.get("shortName"));
    res.setValueCurrency((String) dbo.get("valueCurrency"));
    res.setValueCaption((String) dbo.get("valueCaption"));
    Integer val = (Integer)dbo.get("value");
    res.setValue(BigMoney.parse("RUR "+ val));
    return res;
  }
}