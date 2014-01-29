package com.orange.ru.mongodb.converter;

import com.mongodb.*;
import com.orange.ru.mongodb.reference.bvpncosts.BusinessVpnCosts;
import com.orange.ru.mongodb.reference.bvpncosts.CostChannel;
import com.orange.ru.mongodb.reference.bvpncosts.CostIpVpn;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
/**
 * Конвертер.
 * User: Зайнуллин Радик
 * Date: 11.07.13
 */
public class BusinessVpnCostsWriteConverter implements Converter<BusinessVpnCosts, DBObject> {
  @Override
  public DBObject convert(BusinessVpnCosts src) {
    DBObject dbo = new BasicDBObject();
    ObjectId objId = new ObjectId(src.getId().toString(16));
    dbo.put("_id", objId);
    dbo.put("town", src.getTown());
    dbo.put("info", src.getInfo());
    BasicDBList list = new BasicDBList();
    for (String key: new String[]{"domestic", "okrug", "local"}){
      DBObject curr = new BasicDBObject();
      CostIpVpn costIpVpn = src.getCostsIpVpn().get(key);
      curr.put("name", costIpVpn.getName());
      curr.put("dimension", costIpVpn.getDimension());
      curr.put("description", costIpVpn.getDescription());
      curr.put("value", costIpVpn.getValue().getAmount());
      list.add(curr);
    }
    dbo.put("costsIpVpn", list);


    for (String key: new String[]{"domestic", "okrug", "local"}){
      DBObject curr = new BasicDBObject();
      CostChannel costChannel = src.getCostsChannel().get(key);
      curr.put("name", costChannel.getName());
      curr.put("dimension", costChannel.getDimension());
      curr.put("description", costChannel.getDescription());
      curr.put("value", costChannel.getValue().getAmount());
      list.add(curr);
    }
    dbo.put("costsChannel", list);

    return dbo;
  }
}