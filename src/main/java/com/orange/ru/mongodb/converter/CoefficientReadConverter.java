package com.orange.ru.mongodb.converter;

import com.mongodb.*;
import org.bson.types.ObjectId;
import com.orange.ru.mongodb.coefficient.*;
import org.springframework.core.convert.converter.Converter;
import java.math.BigInteger;
import java.util.*;
/**
 * .
 * User: Зайнуллин Радик
 * Date: 01.08.13
 */
public class CoefficientReadConverter implements Converter<DBObject, Coefficient> {
  @Override
  public Coefficient convert(DBObject dbo) {
    Coefficient out = new Coefficient();
    out.setId(new BigInteger(((ObjectId) dbo.get("_id")).toString(), 16));

    out.setCaption(path4Str("caption", dbo));
    out.setDescription(path4Str("description", dbo));
    out.setSource(path4Str("source", dbo));

    out.getSpeed().setCaption(path4Str("caption", dbo, "speed"));
    out.getSpeed().setDimension(path4Str("dimension", dbo, "speed"));
    out.getSpeed().setValue(path4Int("value", dbo, "speed"));

    Map<String, CoefficientItem> map = out.getCoefficient();
    for( Iterator<Object> it = ((BasicDBList) dbo.get("coefficient")).iterator(); it.hasNext(); ) {
      BasicDBObject curr = (BasicDBObject) it.next();
      CoefficientItem item = new CoefficientItem();
      item.setValue(curr.getDouble("value"));
      map.put(curr.getString("key"), item);
      List<String> names = item.getTownNames();
      item.setTownNames(names);
      for(Iterator<Object> it2=((BasicDBList) curr.get("names")).iterator();it2.hasNext();) {
        names.add((String) it2.next());
      }
    }
    return out;
  }
  private String path4Str(String name, DBObject root, String... args){
    for (String curr: args) root = (DBObject) root.get(curr);
    return (String) root.get(name);
  }
  private Integer path4Int(String name, DBObject root, String... args){
    for (String curr: args) root = (DBObject) root.get(curr);
    return (Integer) root.get(name);
  }
}
