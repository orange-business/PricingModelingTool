package com.orange.ru.mongodb.converter;

import com.mongodb.*;
import com.orange.ru.mongodb.reference.bvpntariffs.*;
import org.bson.types.ObjectId;
import org.joda.money.BigMoney;
import org.springframework.core.convert.converter.Converter;
import java.math.BigInteger;
import java.util.Iterator;
/**
 * .
 * User: Зайнуллин Радик
 * Date: 23.07.13
 */
public class BusinessVpnTariffsReadConverter implements Converter<DBObject, BusinessVpnTariffs> {
  @Override
  public BusinessVpnTariffs convert(DBObject dbo) {
    BusinessVpnTariffs out = new BusinessVpnTariffs();
    out.setId(new BigInteger(((ObjectId) dbo.get("_id")).toString(), 16));
    out.setTown(path4Str("town", dbo));
    out.setCaption(path4Str("caption", dbo));
    out.setDescription(path4Str("description", dbo));
    out.setSource(path4Str("source", dbo));

    out.getBase().setCaption("Скорость, Kbps");
    out.getBase().setDescription("Базовая скорость");
    out.getBase().setDimension("Kbps");
    out.getBase().setValue(2048);

    for( Iterator<Object> it = ((BasicDBList) dbo.get("coverage")).iterator(); it.hasNext(); )  {
      BasicDBObject currCoverage = (BasicDBObject) it.next();
      // domestic
      if (currCoverage.get("name").equals("domestic")) {
        BvpnCoverage domestic = out.getDomestic();
        domestic.setDescription((String) currCoverage.get("description"));
        domestic.setCaption((String) currCoverage.get("caption"));
        // проход по коллекции domestic.tariffications
        for( Iterator<Object> it1 = ((BasicDBList) currCoverage.get("tariffications")).iterator(); it1.hasNext(); )  {
          BasicDBObject currTariffication = (BasicDBObject) it1.next();
          if (currTariffication.get("name").equals("fix")){
            Fix fix = domestic.getFix();
            fix.setCaption((String) currTariffication.get("caption"));
            fix.setDescription((String) currTariffication.get("description"));
            for( Iterator<Object> it2 = ((BasicDBList) currTariffication.get("ports")).iterator(); it2.hasNext(); ){
              BasicDBObject currPort = (BasicDBObject) it2.next();
              if (currPort.getString("name").equals("silver")) fix.setSilver(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("gold")) fix.setGold(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("platinum")) fix.setPlatinum(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
            }
          }
          if (currTariffication.get("name").equals("ubb")){
            Ubb ubb = domestic.getUbb();
            ubb.setCaption((String) currTariffication.get("caption"));
            ubb.setDescription((String) currTariffication.get("description"));
            Mcc mcc = ubb.getMcc();
            mcc.setDescription(path4Str("description", currTariffication, "mcc"));
            mcc.setCaption(path4Str("caption", currTariffication, "mcc"));

            for( Iterator<Object> it4 = ((BasicDBList)((BasicDBObject) currTariffication.get("mcc")).get("ports")).iterator(); it4.hasNext(); ){
              BasicDBObject currPort = (BasicDBObject) it4.next();
              if (currPort.getString("name").equals("silver")) mcc.setSilver(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("gold")) mcc.setGold(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("platinum")) mcc.setPlatinum(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
            }
            Colors colors = ubb.getColors();
            colors.setCaption(path4Str("caption", currTariffication, "service"));
            colors.setDescription(path4Str("description", currTariffication, "service"));
            for( Iterator<Object> it6 = ((BasicDBList)((BasicDBObject) currTariffication.get("service")).get("colors")).iterator(); it6.hasNext(); ){
              BasicDBObject cur = (BasicDBObject) it6.next();
              if (cur.getString("name").equals("data3")) colors.setData3(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("data2")) colors.setData2(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("data1")) colors.setData1(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("voice")) colors.setVoice(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("video")) colors.setVideo(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
            }
          }
        }
      }
      // okrug
      if (currCoverage.get("name").equals("okrug")) {
        BvpnCoverage okrug = out.getOkrug();
        okrug.setDescription((String) currCoverage.get("description"));
        okrug.setCaption((String) currCoverage.get("caption"));
        // проход по коллекции okrug.tariffications
        for( Iterator<Object> it1 = ((BasicDBList) currCoverage.get("tariffications")).iterator(); it1.hasNext(); )  {
          BasicDBObject currTariffication = (BasicDBObject) it1.next();
          if (currTariffication.get("name").equals("fix")){
            Fix fix = okrug.getFix();
            fix.setCaption((String) currTariffication.get("caption"));
            fix.setDescription((String) currTariffication.get("description"));
            for( Iterator<Object> it2 = ((BasicDBList) currTariffication.get("ports")).iterator(); it2.hasNext(); ){
              BasicDBObject currPort = (BasicDBObject) it2.next();
              if (currPort.getString("name").equals("silver")) fix.setSilver(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("gold")) fix.setGold(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("platinum")) fix.setPlatinum(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
            }
          }
          if (currTariffication.get("name").equals("ubb")){
            Ubb ubb = okrug.getUbb();
            ubb.setCaption((String) currTariffication.get("caption"));
            ubb.setDescription((String) currTariffication.get("description"));
            Mcc mcc = ubb.getMcc();
            mcc.setDescription(path4Str("description", currTariffication, "mcc"));
            mcc.setCaption(path4Str("caption", currTariffication, "mcc"));

            for( Iterator<Object> it4 = ((BasicDBList)((BasicDBObject) currTariffication.get("mcc")).get("ports")).iterator(); it4.hasNext(); ){
              BasicDBObject currPort = (BasicDBObject) it4.next();
              if (currPort.getString("name").equals("silver")) mcc.setSilver(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("gold")) mcc.setGold(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("platinum")) mcc.setPlatinum(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
            }
            Colors colors = ubb.getColors();
            colors.setCaption(path4Str("caption", currTariffication, "service"));
            colors.setDescription(path4Str("description", currTariffication, "service"));
            for( Iterator<Object> it6 = ((BasicDBList)((BasicDBObject) currTariffication.get("service")).get("colors")).iterator(); it6.hasNext(); ){
              BasicDBObject cur = (BasicDBObject) it6.next();
              if (cur.getString("name").equals("data3")) colors.setData3(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("data2")) colors.setData2(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("data1")) colors.setData1(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("voice")) colors.setVoice(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("video")) colors.setVideo(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
            }
          }
        }
      }
      // local
      if (currCoverage.get("name").equals("local")) {
        BvpnCoverage local = out.getLocal();
        local.setDescription((String) currCoverage.get("description"));
        local.setCaption((String) currCoverage.get("caption"));
        // проход по коллекции local.tariffications
        for( Iterator<Object> it1 = ((BasicDBList) currCoverage.get("tariffications")).iterator(); it1.hasNext(); )  {
          BasicDBObject currTariffication = (BasicDBObject) it1.next();
          if (currTariffication.get("name").equals("fix")){
            Fix fix = local.getFix();
            fix.setCaption((String) currTariffication.get("caption"));
            fix.setDescription((String) currTariffication.get("description"));
            for( Iterator<Object> it2 = ((BasicDBList) currTariffication.get("ports")).iterator(); it2.hasNext(); ){
              BasicDBObject currPort = (BasicDBObject) it2.next();
              if (currPort.getString("name").equals("silver")) fix.setSilver(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("gold")) fix.setGold(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("platinum")) fix.setPlatinum(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
            }
          }
          if (currTariffication.get("name").equals("ubb")){
            Ubb ubb = local.getUbb();
            ubb.setCaption((String) currTariffication.get("caption"));
            ubb.setDescription((String) currTariffication.get("description"));
            Mcc mcc = ubb.getMcc();
            mcc.setDescription(path4Str("description", currTariffication, "mcc"));
            mcc.setCaption(path4Str("caption", currTariffication, "mcc"));

            for( Iterator<Object> it4 = ((BasicDBList)((BasicDBObject) currTariffication.get("mcc")).get("ports")).iterator(); it4.hasNext(); ){
              BasicDBObject currPort = (BasicDBObject) it4.next();
              if (currPort.getString("name").equals("silver")) mcc.setSilver(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("gold")) mcc.setGold(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
              if (currPort.getString("name").equals("platinum")) mcc.setPlatinum(new PortTyped(currPort.getString("name"), BigMoney.parse("RUR " + currPort.get("value")), currPort.getString("dimension")));
            }
            Colors colors = ubb.getColors();
            colors.setCaption(path4Str("caption", currTariffication, "service"));
            colors.setDescription(path4Str("description", currTariffication, "service"));
            for( Iterator<Object> it6 = ((BasicDBList)((BasicDBObject) currTariffication.get("service")).get("colors")).iterator(); it6.hasNext(); ){
              BasicDBObject cur = (BasicDBObject) it6.next();
              if (cur.getString("name").equals("data3")) colors.setData3(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("data2")) colors.setData2(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("data1")) colors.setData1(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("voice")) colors.setVoice(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
              if (cur.getString("name").equals("video")) colors.setVideo(new Color(path4Str("dimension",cur), BigMoney.parse("RUR " + cur.get("value"))));
            }
          }
        }
      }
    }
    return out;
  }
  private String path4Str(String name, DBObject root, String... args){
    for (String curr: args) root = (DBObject) root.get(curr);
    return (String) root.get(name);
  }
}