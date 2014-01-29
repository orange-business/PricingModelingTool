package com.orange.ru.heap.gson

import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class CountScenarioDbSpec extends spock.lang.Specification {
  @PersistenceContext EntityManager em;

  @Transactional(readOnly=false)
  def setup(){
    int num = em.createNativeQuery("select count(*) from scenario").getSingleResult();
    println "num -> " + num
  }
  def "Сохранение востановленного scenario в базе"() { expect: true }
}