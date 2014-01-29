package com.orange.ru.domain.product.json

import com.orange.ru.domain.Scenario
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import groovy.json.JsonSlurper

@Service("scenarioDeserializer")
@Transactional
class ScenarioDeserializerImpl implements ScenarioDeserializer {
  @Override
  Scenario deserialize(String json) {
    Scenario scenario = new Scenario()
    def slurper = new JsonSlurper()
    def result = slurper.parseText(json)
    scenario.id = result.id
    return scenario
  }
}