package com.orange.ru.json.deserialize

import com.orange.ru.domain.product.json.*
import com.orange.ru.service.ScenarioService
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import org.slf4j.*
import org.springframework.test.annotation.Rollback
import javax.persistence.PersistenceContext
import javax.persistence.EntityManager

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class DeserializationSpock extends Specification {
  static final Logger LOG = LoggerFactory.getLogger(DeserializationSpock.class)
  @PersistenceContext EntityManager em
  @Autowired ScenarioDeserializer scenarioDeserializer
  @Autowired private ScenarioService scenarioService
  @Autowired ScenarioSerializer scenarioSerializer

  def setup(){}
  @Rollback(false)
  def "Имитация сохранение в базе Scenario полученного из интерфейса"() {
    given: "получим уже готовый сценарий из сервиса по id сценария"
    def scenarioGiven = scenarioService.findById(1)
    // сериализуем его
    def json = scenarioSerializer.serialize(scenarioGiven)
    println json
    // десериализуем его
    def scenarioCreated = scenarioDeserializer.deserialize(json)

    expect: "Adding two numbers to return the sum"
    scenarioCreated.id == scenarioGiven.id
    scenarioCreated.ownerEmail == scenarioGiven.ownerEmail
    scenarioCreated.note == scenarioGiven.note
    scenarioCreated.contractTerm == scenarioGiven.contractTerm
    scenarioCreated.lastUpdateDate.toString("yyyy") == scenarioGiven.lastUpdateDate.toString("yyyy")
    scenarioCreated.productItems.size == scenarioGiven.productItems.size



//    when: LOG.debug(json)
//    em.persist(scenario)
//    em.flush()
//    then: true
  }
}