package com.orange.ru.service;

import com.mongodb.DBCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("provisionHelperService")
public class ProductHelperServiceImpl {
  @Autowired
  private MongoTemplate mongoTemplate;
  public void setMongoTemplate(MongoTemplate mongoTemplate) { this.mongoTemplate = mongoTemplate; }

  public List<String> getAllProvisionNames(){
    List<String> names = new ArrayList<String>();
    DBCursor cursor = mongoTemplate.getCollection("provision").find();
    while (cursor.hasNext()) names.add((String) cursor.next().get("name"));
    return names;
  }
  public String getProvisionCodeByName(String provisionName){
    if (provisionName.equals("Access Lines")) return "802.01";
    else if (provisionName.equals("Business VPN")) return "115.00";
    else return "";
  }
  public List<Integer> getAgreementPeriods(){
    List<Integer> agreementPeriods = new ArrayList<Integer>();
    agreementPeriods.add(new Integer(12));
    agreementPeriods.add(new Integer(24));
    agreementPeriods.add(new Integer(36));
    return agreementPeriods;
  }
}
