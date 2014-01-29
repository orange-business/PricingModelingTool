package com.orange.ru.controller;

import com.orange.ru.domain.*;
import com.orange.ru.domain.product.json.ScenarioDeserializer;
import com.orange.ru.domain.product.json.ScenarioSerializer;
import com.orange.ru.service.ScenarioService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/rest/scenarios")
public class ScenarioController {
  static final Logger LOG = LoggerFactory.getLogger(ScenarioController.class);
  @Autowired ScenarioDeserializer scenarioDeserializer;
  public void setScenarioDeserializer(ScenarioDeserializer in) { this.scenarioDeserializer = in; }

  @Autowired private ScenarioService scenarioService;
  public void setScenarioService(ScenarioService scenarioService) { this.scenarioService = scenarioService; }

  @Autowired ScenarioSerializer scenarioSerializer;
  public void setScenarioSerializer(ScenarioSerializer in) { this.scenarioSerializer = in; }

  @Transactional(readOnly = true)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @ResponseBody
  public String getScenarioAsJson(@PathVariable Long id){
    Scenario scenario = scenarioService.findById((long) id);
    return scenarioSerializer.serialize(scenario);
  }

  @Transactional(readOnly = true)
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public void saveJsonAsScenario(@RequestBody String json){
    Scenario scenario = scenarioDeserializer.deserialize(json);
    LOG.debug("scenario.id " + scenario.getId());
  }
}