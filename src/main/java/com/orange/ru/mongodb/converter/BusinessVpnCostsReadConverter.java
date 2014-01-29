package com.orange.ru.mongodb.converter;

import com.mongodb.*;
import com.orange.ru.mongodb.reference.bvpncosts.BusinessVpnCosts;
import com.orange.ru.mongodb.reference.bvpncosts.CostChannel;
import com.orange.ru.mongodb.reference.bvpncosts.CostIpVpn;
import org.joda.money.BigMoney;
import org.springframework.core.convert.converter.Converter;
import org.bson.types.ObjectId;
import java.math.BigInteger;
import java.util.*;
/**
 * Конвертер.
 * User: Зайнуллин Радик
 * Date: 12.07.13
 */
public class BusinessVpnCostsReadConverter implements Converter<DBObject, BusinessVpnCosts> {
  @Override
  public BusinessVpnCosts convert(DBObject dbo) {
    BusinessVpnCosts res = new BusinessVpnCosts();
    ObjectId id = (ObjectId) dbo.get("_id");
    res.setId(new BigInteger(id.toString(), 16));
    res.setTown((String) dbo.get("town"));
    res.setInfo((String) dbo.get("info"));
    res.setCostsIpVpn(new HashMap<String, CostIpVpn>());
    res.setCostsChannel(new HashMap<String, CostChannel>());

    for( Iterator<Object> it = ((BasicDBList) dbo.get("costsIpVpn")).iterator(); it.hasNext(); )  {
      BasicDBObject dbo1 = (BasicDBObject) it.next();
      CostIpVpn costIpVpn = new CostIpVpn();
      costIpVpn.setName((String) dbo1.get("name"));

      costIpVpn.setValue(BigMoney.parse("RUR " + dbo1.get("value")));
      costIpVpn.setDimension((String) dbo1.get("dimension"));
      costIpVpn.setDescription((String) dbo1.get("description"));
      res.getCostsIpVpn().put((String) dbo1.get("name"), costIpVpn);
    }

    for( Iterator<Object> it = ((BasicDBList) dbo.get("costsChannel")).iterator(); it.hasNext(); )  {
      BasicDBObject dbo1 = (BasicDBObject) it.next();
      CostChannel costChannel = new CostChannel();
      costChannel.setName((String) dbo1.get("name"));

      costChannel.setValue(BigMoney.parse("RUR " + dbo1.get("value")));
      costChannel.setDimension((String) dbo1.get("dimension"));
      costChannel.setDescription((String) dbo1.get("description"));
      res.getCostsChannel().put((String) dbo1.get("name"), costChannel);
    }
    return res;
  }
}