package com.orange.ru.json.deserialize

import spock.lang.Specification
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.test.annotation.Rollback
import javax.persistence.PersistenceContext
import javax.persistence.EntityManager

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class DeserializationSpock extends Specification {
  static final Logger LOG = LoggerFactory.getLogger(DeserializationSpock.class)
  @PersistenceContext EntityManager em

  def setup(){

  }
  @Rollback(false)
  def "Сохранение в базе Scenario полученного из интерфейса"() {
//    when: LOG.debug(json)
//
//    em.persist(scenario)
//    em.flush()
//    then: true
  }
}