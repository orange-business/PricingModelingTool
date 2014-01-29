package com.orange.ru.mongodb.converter;

import com.mongodb.*;
import com.orange.ru.mongodb.reference.bvpntariffs.BusinessVpnTariffs;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
/**
 * .
 * User: Зайнуллин Радик
 * Date: 23.07.13
 */
public class BusinessVpnTariffsWriteConverter implements Converter<BusinessVpnTariffs, DBObject> {
  @Override
  public DBObject convert(BusinessVpnTariffs src) {
    DBObject root = new BasicDBObject();
    root.put("_id", new ObjectId(src.getId().toString(16)));

    root.put("town", src.getTown());
    root.put("caption", src.getCaption());
    root.put("description", src.getDescription());
    root.put("source", src.getSource());

    DBObject base = new BasicDBObject();
    root.put("base", base);
    base.put("caption","Скорость, Kbps");
    base.put("description","Базовая скорость");
    base.put("dimension","Kbps");
    base.put("value",2048);

    // domestic
    BasicDBList coverageList = new BasicDBList();
    root.put("coverage", coverageList);
    DBObject domestic = new BasicDBObject();
    coverageList.add(domestic);

    domestic.put("name", "domestic");
    domestic.put("description", src.getDomestic().getDescription());
    domestic.put("caption", src.getDomestic().getCaption());

    BasicDBList domesticTarifficationList = new BasicDBList();
    domestic.put("tariffications", domesticTarifficationList);
    // fix
    DBObject domesticFix = new BasicDBObject();
    domesticTarifficationList.add(domesticFix);
    domesticFix.put("name", "fix");
    domesticFix.put("caption", src.getDomestic().getFix().getCaption());
    domesticFix.put("description", src.getDomestic().getFix().getDescription());

    BasicDBList domesticPortList = new BasicDBList();
    domesticFix.put("ports", domesticPortList);

    // silver
    DBObject domesticPortSilver = new BasicDBObject();
    domesticPortSilver.put("name", "silver");
    domesticPortSilver.put("dimension", src.getDomestic().getFix().getSilver().getDimension());
    domesticPortSilver.put("value", src.getDomestic().getFix().getSilver().getValue().getAmount());
    domesticPortList.add(domesticPortSilver);

    // gold
    DBObject domesticPortGold = new BasicDBObject();
    domesticPortSilver.put("name", "gold");
    domesticPortSilver.put("dimension", src.getDomestic().getFix().getGold().getDimension());
    domesticPortSilver.put("value", src.getDomestic().getFix().getGold().getValue().getAmount());
    domesticPortList.add(domesticPortGold);

    // platinum
    DBObject domesticPortPlatinum = new BasicDBObject();
    domesticPortSilver.put("name", "platinum");
    domesticPortSilver.put("dimension", src.getDomestic().getFix().getPlatinum().getDimension());
    domesticPortSilver.put("value", src.getDomestic().getFix().getPlatinum().getValue().getAmount());
    domesticPortList.add(domesticPortPlatinum);
    // ubb
    DBObject domesticUbb = new BasicDBObject();
    domesticTarifficationList.add(domesticUbb);
    domesticUbb.put("name", "ubb");
    domesticUbb.put("description", src.getDomestic().getUbb().getDescription());
    domesticUbb.put("caption", src.getDomestic().getUbb().getCaption());
    // ubb -- mcc
    DBObject domesticMcc = new BasicDBObject();
    domesticUbb.put("mcc", domesticMcc);

    domesticMcc.put("caption", src.getDomestic().getUbb().getMcc().getCaption());
    domesticMcc.put("description", src.getDomestic().getUbb().getMcc().getDescription());
    // ubb -- mcc -- silver
    BasicDBList domesticMccPortList = new BasicDBList();
    domesticUbb.put("ports", domesticMccPortList);

    DBObject domesticSilverMcc = new BasicDBObject();
    domesticMcc.put("silver", domesticSilverMcc);
    domesticSilverMcc.put("name", src.getDomestic().getUbb().getMcc().getSilver().getName());
    domesticSilverMcc.put("value", src.getDomestic().getUbb().getMcc().getSilver().getValue().getAmount());
    domesticSilverMcc.put("dimension", src.getDomestic().getUbb().getMcc().getSilver().getDimension());
    // gold
    DBObject domesticGoldMcc = new BasicDBObject();
    domesticMcc.put("gold", domesticGoldMcc);
    domesticGoldMcc.put("name", src.getDomestic().getUbb().getMcc().getGold().getName());
    domesticGoldMcc.put("value", src.getDomestic().getUbb().getMcc().getGold().getValue().getAmount());
    domesticGoldMcc.put("dimension", src.getDomestic().getUbb().getMcc().getGold().getDimension());

    // platinum
    DBObject domesticPlatinumMcc  = new BasicDBObject();
    domesticMcc.put("platinum", domesticPlatinumMcc);
    domesticPlatinumMcc.put("name", src.getDomestic().getUbb().getMcc().getPlatinum().getName());
    domesticPlatinumMcc.put("value", src.getDomestic().getUbb().getMcc().getPlatinum().getValue().getAmount());
    domesticPlatinumMcc.put("dimension", src.getDomestic().getUbb().getMcc().getPlatinum().getDimension());

    // service
    DBObject domesticService = new BasicDBObject();
    domesticUbb.put("service", domesticService);
    domesticService.put("caption", src.getDomestic().getUbb().getColors().getCaption());
    domesticService.put("description", src.getDomestic().getUbb().getColors().getDescription());
    // colors
    BasicDBList domesticColorList = new BasicDBList();
    domesticService.put("colors", domesticColorList);

    // data3
    DBObject domesticData3 = new BasicDBObject();
    domesticData3.put("name", "data3");
    domesticData3.put("value", src.getDomestic().getUbb().getColors().getData3().getValue().getAmount());
    domesticData3.put("dimension", src.getDomestic().getUbb().getColors().getData3().getDimension());
    domesticColorList.add(domesticData3);

    // data2
    DBObject domesticData2 = new BasicDBObject();
    domesticData2.put("name", "data2");
    domesticData2.put("value", src.getDomestic().getUbb().getColors().getData2().getValue().getAmount());
    domesticData2.put("dimension", src.getDomestic().getUbb().getColors().getData2().getDimension());
    domesticColorList.add(domesticData2);

    // data1
    DBObject domesticData1 = new BasicDBObject();
    domesticData1.put("name", "data1");
    domesticData1.put("value", src.getDomestic().getUbb().getColors().getData1().getValue().getAmount());
    domesticData1.put("dimension", src.getDomestic().getUbb().getColors().getData1().getDimension());
    domesticColorList.add(domesticData1);

    // Voice
    DBObject domesticVoice = new BasicDBObject();
    domesticVoice.put("name", "voice");
    domesticVoice.put("value", src.getDomestic().getUbb().getColors().getVoice().getValue().getAmount());
    domesticVoice.put("dimension", src.getDomestic().getUbb().getColors().getVoice().getDimension());
    domesticColorList.add(domesticVoice);

    // video
    DBObject domesticVideo = new BasicDBObject();
    domesticVideo.put("name", "video");
    domesticVideo.put("value", src.getDomestic().getUbb().getColors().getVideo().getValue().getAmount());
    domesticVideo.put("dimension", src.getDomestic().getUbb().getColors().getVideo().getDimension());
    domesticColorList.add(domesticVideo);

    // okrug
    DBObject okrug = new BasicDBObject();
    coverageList.add(okrug);

    okrug.put("name", "okrug");
    okrug.put("description", src.getOkrug().getDescription());
    okrug.put("caption", src.getOkrug().getCaption());

    BasicDBList okrugTarifficationList = new BasicDBList();
    okrug.put("tariffications", okrugTarifficationList);
    // fix
    DBObject okrugFix = new BasicDBObject();
    okrugTarifficationList.add(okrugFix);
    okrugFix.put("name", "fix");
    okrugFix.put("caption", src.getOkrug().getFix().getCaption());
    okrugFix.put("description", src.getOkrug().getFix().getDescription());

    BasicDBList okrugPortList = new BasicDBList();
    okrugFix.put("ports", okrugPortList);

    // silver
    DBObject okrugPortSilver = new BasicDBObject();
    okrugPortSilver.put("name", "silver");
    okrugPortSilver.put("dimension", src.getOkrug().getFix().getSilver().getDimension());
    okrugPortSilver.put("value", src.getOkrug().getFix().getSilver().getValue().getAmount());
    okrugPortList.add(okrugPortSilver);

    // gold
    DBObject okrugPortGold = new BasicDBObject();
    okrugPortSilver.put("name", "gold");
    okrugPortSilver.put("dimension", src.getOkrug().getFix().getGold().getDimension());
    okrugPortSilver.put("value", src.getOkrug().getFix().getGold().getValue().getAmount());
    okrugPortList.add(okrugPortGold);

    // platinum
    DBObject okrugPortPlatinum = new BasicDBObject();
    okrugPortSilver.put("name", "platinum");
    okrugPortSilver.put("dimension", src.getOkrug().getFix().getPlatinum().getDimension());
    okrugPortSilver.put("value", src.getOkrug().getFix().getPlatinum().getValue().getAmount());
    okrugPortList.add(okrugPortPlatinum);
    // ubb
    DBObject okrugUbb = new BasicDBObject();
    okrugTarifficationList.add(okrugUbb);
    okrugUbb.put("name", "ubb");
    okrugUbb.put("description", src.getOkrug().getUbb().getDescription());
    okrugUbb.put("caption", src.getOkrug().getUbb().getCaption());
    // ubb -- mcc
    DBObject okrugMcc = new BasicDBObject();
    okrugUbb.put("mcc", okrugMcc);

    okrugMcc.put("caption", src.getOkrug().getUbb().getMcc().getCaption());
    okrugMcc.put("description", src.getOkrug().getUbb().getMcc().getDescription());
    // ubb -- mcc -- silver
    BasicDBList okrugMccPortList = new BasicDBList();
    okrugUbb.put("ports", okrugMccPortList);

    DBObject okrugSilverMcc = new BasicDBObject();
    okrugMcc.put("silver", okrugSilverMcc);
    okrugSilverMcc.put("name", src.getOkrug().getUbb().getMcc().getSilver().getName());
    okrugSilverMcc.put("value", src.getOkrug().getUbb().getMcc().getSilver().getValue().getAmount());
    okrugSilverMcc.put("dimension", src.getOkrug().getUbb().getMcc().getSilver().getDimension());
    // gold
    DBObject okrugGoldMcc = new BasicDBObject();
    okrugMcc.put("gold", okrugGoldMcc);
    okrugGoldMcc.put("name", src.getOkrug().getUbb().getMcc().getGold().getName());
    okrugGoldMcc.put("value", src.getOkrug().getUbb().getMcc().getGold().getValue().getAmount());
    okrugGoldMcc.put("dimension", src.getOkrug().getUbb().getMcc().getGold().getDimension());

    // platinum
    DBObject okrugPlatinumMcc  = new BasicDBObject();
    okrugMcc.put("platinum", okrugPlatinumMcc);
    okrugPlatinumMcc.put("name", src.getOkrug().getUbb().getMcc().getPlatinum().getName());
    okrugPlatinumMcc.put("value", src.getOkrug().getUbb().getMcc().getPlatinum().getValue().getAmount());
    okrugPlatinumMcc.put("dimension", src.getOkrug().getUbb().getMcc().getPlatinum().getDimension());

    // service
    DBObject okrugService = new BasicDBObject();
    okrugUbb.put("service", okrugService);
    okrugService.put("caption", src.getOkrug().getUbb().getColors().getCaption());
    okrugService.put("description", src.getOkrug().getUbb().getColors().getDescription());
    // colors
    BasicDBList okrugColorList = new BasicDBList();
    okrugService.put("colors", okrugColorList);

    // data3
    DBObject okrugData3 = new BasicDBObject();
    okrugData3.put("name", "data3");
    okrugData3.put("value", src.getOkrug().getUbb().getColors().getData3().getValue().getAmount());
    okrugData3.put("dimension", src.getOkrug().getUbb().getColors().getData3().getDimension());
    okrugColorList.add(okrugData3);

    // data2
    DBObject okrugData2 = new BasicDBObject();
    okrugData2.put("name", "data2");
    okrugData2.put("value", src.getOkrug().getUbb().getColors().getData2().getValue().getAmount());
    okrugData2.put("dimension", src.getOkrug().getUbb().getColors().getData2().getDimension());
    okrugColorList.add(okrugData2);

    // data1
    DBObject okrugData1 = new BasicDBObject();
    okrugData1.put("name", "data1");
    okrugData1.put("value", src.getOkrug().getUbb().getColors().getData1().getValue().getAmount());
    okrugData1.put("dimension", src.getOkrug().getUbb().getColors().getData1().getDimension());
    okrugColorList.add(okrugData1);

    // Voice
    DBObject okrugVoice = new BasicDBObject();
    okrugVoice.put("name", "voice");
    okrugVoice.put("value", src.getOkrug().getUbb().getColors().getVoice().getValue().getAmount());
    okrugVoice.put("dimension", src.getOkrug().getUbb().getColors().getVoice().getDimension());
    okrugColorList.add(okrugVoice);

    // video
    DBObject okrugVideo = new BasicDBObject();
    okrugVideo.put("name", "video");
    okrugVideo.put("value", src.getOkrug().getUbb().getColors().getVideo().getValue().getAmount());
    okrugVideo.put("dimension", src.getOkrug().getUbb().getColors().getVideo().getDimension());
    okrugColorList.add(okrugVideo);    
    
    // local
    DBObject local = new BasicDBObject();
    coverageList.add(local);

    local.put("name", "local");
    local.put("description", src.getLocal().getDescription());
    local.put("caption", src.getLocal().getCaption());

    BasicDBList localTarifficationList = new BasicDBList();
    local.put("tariffications", localTarifficationList);
    // fix
    DBObject localFix = new BasicDBObject();
    localTarifficationList.add(localFix);
    localFix.put("name", "fix");
    localFix.put("caption", src.getLocal().getFix().getCaption());
    localFix.put("description", src.getLocal().getFix().getDescription());

    BasicDBList localPortList = new BasicDBList();
    localFix.put("ports", localPortList);

    // silver
    DBObject localPortSilver = new BasicDBObject();
    localPortSilver.put("name", "silver");
    localPortSilver.put("dimension", src.getLocal().getFix().getSilver().getDimension());
    localPortSilver.put("value", src.getLocal().getFix().getSilver().getValue().getAmount());
    localPortList.add(localPortSilver);

    // gold
    DBObject localPortGold = new BasicDBObject();
    localPortSilver.put("name", "gold");
    localPortSilver.put("dimension", src.getLocal().getFix().getGold().getDimension());
    localPortSilver.put("value", src.getLocal().getFix().getGold().getValue().getAmount());
    localPortList.add(localPortGold);

    // platinum
    DBObject localPortPlatinum = new BasicDBObject();
    localPortSilver.put("name", "platinum");
    localPortSilver.put("dimension", src.getLocal().getFix().getPlatinum().getDimension());
    localPortSilver.put("value", src.getLocal().getFix().getPlatinum().getValue().getAmount());
    localPortList.add(localPortPlatinum);
    // ubb
    DBObject localUbb = new BasicDBObject();
    localTarifficationList.add(localUbb);
    localUbb.put("name", "ubb");
    localUbb.put("description", src.getLocal().getUbb().getDescription());
    localUbb.put("caption", src.getLocal().getUbb().getCaption());
    // ubb -- mcc
    DBObject localMcc = new BasicDBObject();
    localUbb.put("mcc", localMcc);

    localMcc.put("caption", src.getLocal().getUbb().getMcc().getCaption());
    localMcc.put("description", src.getLocal().getUbb().getMcc().getDescription());
    // ubb -- mcc -- silver
    BasicDBList localMccPortList = new BasicDBList();
    localUbb.put("ports", localMccPortList);

    DBObject localSilverMcc = new BasicDBObject();
    localMcc.put("silver", localSilverMcc);
    localSilverMcc.put("name", src.getLocal().getUbb().getMcc().getSilver().getName());
    localSilverMcc.put("value", src.getLocal().getUbb().getMcc().getSilver().getValue().getAmount());
    localSilverMcc.put("dimension", src.getLocal().getUbb().getMcc().getSilver().getDimension());
    // gold
    DBObject localGoldMcc = new BasicDBObject();
    localMcc.put("gold", localGoldMcc);
    localGoldMcc.put("name", src.getLocal().getUbb().getMcc().getGold().getName());
    localGoldMcc.put("value", src.getLocal().getUbb().getMcc().getGold().getValue().getAmount());
    localGoldMcc.put("dimension", src.getLocal().getUbb().getMcc().getGold().getDimension());

    // platinum
    DBObject localPlatinumMcc  = new BasicDBObject();
    localMcc.put("platinum", localPlatinumMcc);
    localPlatinumMcc.put("name", src.getLocal().getUbb().getMcc().getPlatinum().getName());
    localPlatinumMcc.put("value", src.getLocal().getUbb().getMcc().getPlatinum().getValue().getAmount());
    localPlatinumMcc.put("dimension", src.getLocal().getUbb().getMcc().getPlatinum().getDimension());

    // service
    DBObject localService = new BasicDBObject();
    localUbb.put("service", localService);
    localService.put("caption", src.getLocal().getUbb().getColors().getCaption());
    localService.put("description", src.getLocal().getUbb().getColors().getDescription());
    // colors
    BasicDBList localColorList = new BasicDBList();
    localService.put("colors", localColorList);

    // data3
    DBObject localData3 = new BasicDBObject();
    localData3.put("name", "data3");
    localData3.put("value", src.getLocal().getUbb().getColors().getData3().getValue().getAmount());
    localData3.put("dimension", src.getLocal().getUbb().getColors().getData3().getDimension());
    localColorList.add(localData3);

    // data2
    DBObject localData2 = new BasicDBObject();
    localData2.put("name", "data2");
    localData2.put("value", src.getLocal().getUbb().getColors().getData2().getValue().getAmount());
    localData2.put("dimension", src.getLocal().getUbb().getColors().getData2().getDimension());
    localColorList.add(localData2);

    // data1
    DBObject localData1 = new BasicDBObject();
    localData1.put("name", "data1");
    localData1.put("value", src.getLocal().getUbb().getColors().getData1().getValue().getAmount());
    localData1.put("dimension", src.getLocal().getUbb().getColors().getData1().getDimension());
    localColorList.add(localData1);

    // Voice
    DBObject localVoice = new BasicDBObject();
    localVoice.put("name", "voice");
    localVoice.put("value", src.getLocal().getUbb().getColors().getVoice().getValue().getAmount());
    localVoice.put("dimension", src.getLocal().getUbb().getColors().getVoice().getDimension());
    localColorList.add(localVoice);

    // video
    DBObject localVideo = new BasicDBObject();
    localVideo.put("name", "video");
    localVideo.put("value", src.getLocal().getUbb().getColors().getVideo().getValue().getAmount());
    localVideo.put("dimension", src.getLocal().getUbb().getColors().getVideo().getDimension());
    localColorList.add(localVideo);
    return root;
  }
}