package com.orange.ru.mongodb.repositories

import com.orange.ru.mongodb.coefficient.Coefficient
import com.orange.ru.mongodb.coefficient.CoefficientItem
import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
/**
 *
 * User: Зайнуллин Радик
 * Date: 02.08.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class])
class CoefficientRepositoryImplTest extends GroovyTestCase {
  def sample = '''
{
  "_id" : 25370882613953600434367867727,
  "caption" : "Коэффициенты расчета в зависимости от скорости и города",
  "description" : "Коэффициенты расчета в зависимости от скорости и города",
  "source" : "Копия BVPN for PT 08042013.xlsx",
  "speed" : {
    "caption" : "скорость подключения",
    "value" : 192,
    "dimension" : "Кбит/с"
  },
  "coefficient" : [{
      "key" : "moscow",
      "value" : 0.13,
      "names" : ["Москва", "Moscow"]
    }, {
      "key" : "petersburg",
      "value" : 0.13,
      "names" : ["Петербург", "Petersburg"]
    }, {
      "key" : "other",
      "value" : 0.13,
      "names" : ["другие", "others"]
    }]
}
'''

  @Autowired
  CoefficientRepository coefficientRepository

  def coefficient
  @Before
  public void setUp() throws Exception {
    coefficient = new Coefficient()
    coefficient.id = 25370882613953600434367867726
    coefficient.caption = "Коэффициенты расчета в зависимости от скорости и города"
    coefficient.description = "Коэффициенты расчета в зависимости от скорости и города"
    coefficient.source = "Копия BVPN for PT 08042013.xlsx"

    coefficient.speed.caption = "скорость подключения для скорости 192 Кбит/с"
    coefficient.speed.value = 128
    coefficient.speed.dimension = "Кбит/с"

    CoefficientItem item = new CoefficientItem()
    // Moscow
    item.townNames.add("Москва")
    item.townNames.add("Moscow")
    item.value = 0.1
    coefficient.coefficient.put("moscow", item)
    // Petersburg
    item.townNames.add("Петербург")
    item.townNames.add("Petersburg")
    item.value = 0.1
    coefficient.coefficient.put("petersburg", item)
    // other
    item.townNames.add("другие")
    item.townNames.add("others")
    item.value = 0.1
    coefficient.coefficient.put("other", item)
  }

  @Test
  //@Ignore
  void testFindBySpeed() {
    Coefficient res = coefficientRepository.findBySpeed(192)

    def slurper = new JsonSlurper()
    def sampleObj = slurper.parseText(sample)

    println sampleObj.source
    assert sampleObj.source == res.source
  }
  @Test
  void testSave(){
    coefficientRepository.save(coefficient)
  }
}
