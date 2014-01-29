package com.orange.ru.heap.gson

import com.orange.ru.domain.*
import com.orange.ru.domain.product.renew.HandleScenario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import com.orange.ru.domain.product.gson.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import org.springframework.test.annotation.Rollback

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class DeserializedScenarioSaveToDbSpec extends spock.lang.Specification {
  @PersistenceContext EntityManager em;
  @Autowired HandleScenario handleScenario
  Scenario recoved; String json =  new File('src/test/resources/test5.json').getText('UTF-8')

  def setup(){
    recoved = GsonUtil.gson.fromJson(json, Scenario.class)
    handleScenario.calculate recoved
  }
  @Transactional
  @Rollback(false)
  def "Сохранение востановленного scenario в базе"() {
    // проверка
    em.persist(recoved)
    em.flush()
    println "em.contains(recoved) -> " + em.contains(recoved)
    println "num -> " + em.createNativeQuery("select count(*) from scenario").getSingleResult()
    expect: em
  }
}