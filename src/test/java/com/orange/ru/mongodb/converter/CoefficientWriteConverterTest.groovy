package com.orange.ru.mongodb.converter

import com.mongodb.DBObject
import com.orange.ru.mongodb.coefficient.*
import groovy.json.JsonSlurper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.*
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
/**
 * User: Зайнуллин Радик
 * Date: 01.08.13
 * DBObject convert(Coefficient src)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class])
class CoefficientWriteConverterTest extends GroovyTestCase {
  def jsonStr = '''
{
  "_id" : "51fa4d5e656ac3d91bdbab4e",
  "caption" : "Коэффициенты расчета в зависимости от скорости и города",
  "description" : "Коэффициенты расчета в зависимости от скорости и города",
  "source" : "Копия BVPN for PT 08042013.xlsx",
  "speed" : {
    "caption" : "скорость подключения",
    "value" : 128,
    "dimension" : "Кбит/с"
  },
  "coefficient" : [{
      "key" : "moscow",
      "value" : 0.1,
      "names" : ["Москва", "Moscow"]
    }, {
      "key" : "petersburg",
      "value" : 0.1,
      "names" : ["Петербург", "Petersburg"]
    }, {
      "key" : "other",
      "value" : 0.1,
      "names" : ["другие", "others"]
    }]
}
  '''
  def sample
  @Before
  public void setUp() throws Exception {
    sample = new Coefficient()
    sample.id = 25370882613953600434367867726
    sample.caption = "Коэффициенты расчета в зависимости от скорости и города"
    sample.description = "Коэффициенты расчета в зависимости от скорости и города"
    sample.source = "Копия BVPN for PT 08042013.xlsx"

    sample.speed.caption = "скорость подключения"
    sample.speed.value = 128
    sample.speed.dimension = "Кбит/с"

    CoefficientItem item = new CoefficientItem()
    // Moscow
    item.townNames.add("Москва")
    item.townNames.add("Moscow")
    item.value = 0.1
    sample.coefficient.put("moscow", item)
    // Petersburg
    item.townNames.add("Петербург")
    item.townNames.add("Petersburg")
    item.value = 0.1
    sample.coefficient.put("petersburg", item)
    // other
    item.townNames.add("другие")
    item.townNames.add("others")
    item.value = 0.1
    sample.coefficient.put("other", item)
  }

  @Test
  void testConvert() {
    DBObject converted = new CoefficientWriteConverter().convert(sample)

    def slurper = new JsonSlurper()
    def result1 = slurper.parseText(jsonStr)
    def result2 = slurper.parseText(converted.toString())

    println result1._id
//    assert result1._id == result2._id
    println result1.source
    assert result1.source == result2.source
    println result1.caption
    assert result1.caption == result2.caption
    println result1.description
    assert result1.description == result2.description
    println result1.speed.caption
    assert result1.speed.caption == result2.speed.caption
    println result1.speed.value
    assert result1.speed.value == result2.speed.value
    println result1.speed.dimension
    assert result1.speed.dimension == result2.speed.dimension

    println result1.coefficient[0]
    assert result1.coefficient[0] == result2.coefficient[0]
    println result1.coefficient[1]
    assert result1.coefficient[1] == result2.coefficient[1]
    println result1.coefficient[2]
    assert result1.coefficient[2] == result2.coefficient[2]
  }
}