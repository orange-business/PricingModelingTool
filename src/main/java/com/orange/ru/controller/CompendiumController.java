package com.orange.ru.controller;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.*;
import java.util.regex.*;
/**
 * Обслуживание справочников
 * User: radik
 * Date: 12.06.13
 */
@Controller
public class CompendiumController {
  @Autowired
  private MongoTemplate mongoTemplate;

  @RequestMapping(value = "/getManHour", method = RequestMethod.GET)
  public @ResponseBody JSONObject getManHour(HttpServletRequest req, HttpServletResponse res) throws Exception {
    DBCursor cursor = mongoTemplate.getCollection("hour").find();
    int i = 1;
    JSONArray rows = new JSONArray();
    while (cursor.hasNext()) rows.add(convert(cursor.next().toString(), i++));
    JSONObject json = new JSONObject();
    json.put("total", 1);
    json.put("page", 1);
    json.put("records", rows.size());
    json.put("rows", rows);
    return json;
  }

  @RequestMapping(value = "/editManHour", method = RequestMethod.POST)
  public ModelAndView editManHour(@RequestBody String request) throws Exception {
    DBCollection collection =  mongoTemplate.getCollection("hour");
    String id = extract(request, "id");
    String value = extract(request, "value");

    BasicDBObject updateQuery = new BasicDBObject();
    updateQuery.append("$set", new BasicDBObject().append("value", value));

    BasicDBObject searchQuery = new BasicDBObject();
    DBObject searchById = new BasicDBObject("_id", new ObjectId(id));
    collection.update(searchById, updateQuery);
    return null;
  }

  private static String extract(String request, String param){
    Pattern p1 = Pattern.compile(param + "\\=([^\\&]+)");
    Matcher m1 = p1.matcher(request);
    m1.find();
    return m1.group(1);
  }

  //[{"_id":{"$oid":"51b6994cff1ee695381df850"},"value":2468,"longName":"Sales TOP100","group":"Sales","cost":"CostST100"}, {"_id":{"$oid":"51b6994cff1ee695381df851"},"value":1770,"longName":"Sales Corporates","group":"Sales","cost":"CostSCorp"},{"_id":{"$oid":"51b6994cff1ee695381df852"},"value":1902,"longName":"Sales SME","group":"Sales","cost":"CostSSME"},{"_id":{"$oid":"51b6994cff1ee695381df853"},"value":1397,"longName":"Presales","group":"Sales","cost":"CostPreS"},{"_id":{"$oid":"51b6994cff1ee695381df854"},"value":1351,"longName":"Field Operations","group":"Operations","cost":"CostFO"},{"_id":{"$oid":"51b6994cff1ee695381df855"},"value":1783,"longName":"Project Management (provisioning)","group":"Operations","cost":"CostPM"},{"_id":{"$oid":"51b6994cff1ee695381df856"},"value":2050,"longName":"Professional Services (Consulting)","group":"Operations","cost":"CostPS"},{"_id":{"$oid":"51b6994cff1ee695381df857"},"value":1261,"longName":"Customer Support","group":"Operations","cost":"CostCS"},{"_id":{"$oid":"51b6994cff1ee695381df858"},"value":723,"longName":"Contact Center","group":"Operations","cost":"CostCC"},{"_id":{"$oid":"51b6994cff1ee695381df859"},"value":1614,"longName":"Sourcing","group":"Operations","cost":"CostSours"},{"_id":{"$oid":"51b6994cff1ee695381df85a"},"value":1309,"longName":"Order Management","group":"Operations","cost":"CostOM"},{"_id":{"$oid":"51b6994cff1ee695381df85b"},"value":1296,"longName":"Billing","group":"Operations","cost":"CostBill"},{"_id":{"$oid":"51b6994cff1ee695381df85c"},"value":1487,"longName":"CSM","group":"Operations","cost":"CostCSM"},{"_id":{"$oid":"51b6994cff1ee695381df85d"},"value":1555,"longName":"Finance","group":"Finance","cost":"CostFin"},{"_id":{"$oid":"51b6994cff1ee695381df85e"},"value":2031,"longName":"Legal","group":"Legal","cost":"CostLegal"},{"_id":{"$oid":"51b6994cff1ee695381df85f"},"value":1582,"longName":"Technical RBNF","group":"RBNF","cost":"CostTecRBNF"}]
  public static JSONObject convert(String src, int i) throws Exception { // {"id":1,"cell":["CostST100",2468]} - формируемая строка
    JSONParser parser = new JSONParser();
    JSONObject obj = (JSONObject) parser.parse(src);
    JSONObject json = new JSONObject();
    json.put("id", ((JSONObject) obj.get("_id")).get("$oid"));
    JSONArray arr = new JSONArray();
    arr.add(i);
    arr.add(obj.get("cost"));
    arr.add(obj.get("value"));
    json.put("cell", arr);
    return json;
  }
  public void setMongoTemplate(MongoTemplate mongoTemplate) { this.mongoTemplate = mongoTemplate; }
}