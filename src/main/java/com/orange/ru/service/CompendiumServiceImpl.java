package com.orange.ru.service;

import com.mongodb.*;
import com.orange.ru.mongodb.coefficient.Coefficient;
import com.orange.ru.mongodb.repositories.CoefficientRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("compendiumService")
public class CompendiumServiceImpl implements CompendiumService {
  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private CoefficientRepository coefficientRepository;

  @Override
  public List<Integer> getSpeedValues() {
    List<Coefficient> coefficientList = coefficientRepository.findAll();
    List<Integer> list = new ArrayList<>();
    for (Coefficient curr: coefficientList){
      list.add(curr.getSpeed().getValue());
    }
    return list;
  }

  public List<String> getTarifficationPlanValues(){
    List<String> list = new ArrayList<String>();
    DBCollection coll = mongoTemplate.getCollection("tariffication");
    DBCursor cursor = coll.find();
    while (cursor.hasNext()) list.add((String)cursor.next().get("name"));
    return list;
  }

  @Override
  public JSONArray retrieveUnitCostPerHour() throws Exception {
    List<String> list = new ArrayList<String>();
    DBCollection coll = mongoTemplate.getCollection("hour");
    DBCursor cursor = coll.find();
    while (cursor.hasNext()) list.add(cursor.next().toString());

    JSONParser parser = new JSONParser();
    JSONArray array = new JSONArray();
    for (String str: list) array.add(parser.parse(str));
    return array;
  }
  public void setMongoTemplate(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }
}