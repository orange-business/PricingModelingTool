package com.orange.ru.mongodb.converter

import com.mongodb.DBObject
import com.orange.ru.mongodb.reference.bvpntariffs.BusinessVpnTariffs
import org.joda.money.Money
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
/**
 * .
 * User: Зайнуллин Радик
 * Date: 31.07.13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class])
class BusinessVpnTariffsWriteConverterTest extends GroovyTestCase {
  BusinessVpnTariffs sample
  def jsonStr = '''
{
  "_id" : { "$oid" : "51f7ae3f69bd0ce783ebdec3"},
  "town" : "Биробиджан",
  "caption" : "IPVPN Availability Matrix",
  "description" : "IPVPN Availability Matrix",
  "source" : "BVPN Tariffs for PT 29052013.xlsx",
  "coverage" : [{
      "caption" : "domestic область покрытия",
      "tariffications" : [{
          "caption" : "IP VPN Domestic, FIX (руб.)",
          "ports" : [{
              "value" : 111500,
              "dimension" : "RUR",
              "name" : "silver"
            }, {
              "value" : 145800,
              "dimension" : "RUR",
              "name" : "gold"
            }, {
              "value" : 189500,
              "dimension" : "RUR",
              "name" : "platinum"
            }],
          "description" : "IP VPN Domestic, FIX (руб.)",
          "name" : "fix"
        }, {
          "mcc" : {
            "caption" : "МСС, руб.",
            "ports" : [{
                "value" : 20100,
                "dimension" : "RUR",
                "name" : "silver"
              }, {
                "value" : 26300,
                "dimension" : "RUR",
                "name" : "gold"
              }, {
                "value" : 34200,
                "dimension" : "RUR",
                "name" : "platinum"
              }],
            "description" : "mcc - минимальная сумма счета"
          },
          "caption" : "IP VPN Domestic, UBB (руб.)",
          "service" : {
            "caption" : "Стоимость 1 Мб исходящего трафика по классам сервиса",
            "colors" : [{
                "value" : 1.23,
                "dimension" : "RUR",
                "name" : "data3"
              }, {
                "value" : 1.42,
                "dimension" : "RUR",
                "name" : "data2"
              }, {
                "value" : 1.67,
                "dimension" : "RUR",
                "name" : "data1"
              }, {
                "value" : 1.85,
                "dimension" : "RUR",
                "name" : "voice"
              }, {
                "value" : 1.91,
                "dimension" : "RUR",
                "name" : "video"
              }],
            "description" : "Стоимость 1 Мб исходящего трафика по классам сервиса"
          },
          "description" : "IP VPN Domestic, UBB (руб.)",
          "name" : "ubb"
        }],
      "description" : "domestic область покрытия",
      "name" : "domestic"
    }, {
      "caption" : "okrug область покрытия",
      "tariffications" : [{
          "caption" : "IP VPN Okrug, FIX (руб.)",
          "ports" : [{
              "value" : 44600,
              "dimension" : "RUR",
              "name" : "silver"
            }, {
              "value" : 58400,
              "dimension" : "RUR",
              "name" : "gold"
            }, {
              "value" : 75800,
              "dimension" : "RUR",
              "name" : "platinum"
            }],
          "description" : "IP VPN Okrug, FIX (руб.)",
          "name" : "fix"
        }, {
          "mcc" : {
            "caption" : "МСС, руб.",
            "ports" : [{
                "value" : 8100,
                "dimension" : "RUR",
                "name" : "silver"
              }, {
                "value" : 10600,
                "dimension" : "RUR",
                "name" : "gold"
              }, {
                "value" : 13700,
                "dimension" : "RUR",
                "name" : "platinum"
              }],
            "description" : "mcc - минимальная сумма счета"
          },
          "caption" : "IP VPN Okrug, UBB (руб.)",
          "service" : {
            "caption" : "Стоимость 1 Мб исходящего трафика по классам сервиса",
            "colors" : [{
                "value" : 0.5,
                "dimension" : "RUR",
                "name" : "data3"
              }, {
                "value" : 0.57,
                "dimension" : "RUR",
                "name" : "data2"
              }, {
                "value" : 0.67,
                "dimension" : "RUR",
                "name" : "data1"
              }, {
                "value" : 0.74,
                "dimension" : "RUR",
                "name" : "voice"
              }, {
                "value" : 0.77,
                "dimension" : "RUR",
                "name" : "video"
              }],
            "description" : "Стоимость 1 Мб исходящего трафика по классам сервиса"
          },
          "description" : "IP VPN Okrug, UBB (руб.)",
          "name" : "ubb"
        }],
      "description" : "okrug область покрытия",
      "name" : "okrug"
    }, {
      "caption" : "local область покрытия",
      "tariffications" : [{
          "caption" : "IP VPN Local, FIX (руб.)",
          "ports" : [{
              "value" : 3300,
              "dimension" : "RUR",
              "name" : "silver"
            }, {
              "value" : 4400,
              "dimension" : "RUR",
              "name" : "gold"
            }, {
              "value" : 5600,
              "dimension" : "RUR",
              "name" : "platinum"
            }],
          "description" : "IP VPN Local, FIX (руб.)",
          "name" : "fix"
        }, {
          "mcc" : {
            "caption" : "МСС, руб.",
            "ports" : [{
                "value" : 700,
                "dimension" : "RUR",
                "name" : "silver"
              }, {
                "value" : 900,
                "dimension" : "RUR",
                "name" : "gold"
              }, {
                "value" : 1200,
                "dimension" : "RUR",
                "name" : "platinum"
              }],
            "description" : "mcc - минимальная сумма счета"
          },
          "caption" : "IP VPN Local, UBB (руб.)",
          "service" : {
            "caption" : "Стоимость 1 Мб исходящего трафика по классам сервиса",
            "colors" : [{
                "value" : 0.18,
                "dimension" : "RUR",
                "name" : "data3"
              }, {
                "value" : 0.2,
                "dimension" : "RUR",
                "name" : "data2"
              }, {
                "value" : 0.24,
                "dimension" : "RUR",
                "name" : "data1"
              }, {
                "value" : 0.26,
                "dimension" : "RUR",
                "name" : "voice"
              }, {
                "value" : 0.27,
                "dimension" : "RUR",
                "name" : "video"
              }],
            "description" : "Стоимость 1 Мб исходящего трафика по классам сервиса"
          },
          "description" : "IP VPN Local, UBB (руб.)",
          "name" : "ubb"
        }],
      "description" : "local область покрытия",
      "name" : "local"
    }]
}
'''
  @Before
  public void setUp() throws Exception {
    sample = new BusinessVpnTariffs();
    sample.id = 25367713334505920267899625155  // 51f7ae3f69bd0ce783ebdec3
    sample.town = "Биробиджан"
    sample.caption = "IPVPN Availability Matrix"
    sample.description = "IPVPN Availability Matrix"
    sample.source = "BVPN Tariffs for PT 29052013.xlsx"
    // domestic
    sample.domestic.caption = "domestic область покрытия"
    sample.domestic.description = "domestic область покрытия"
    // domestic fix
    sample.domestic.fix.caption = "IP VPN Domestic, FIX (руб.)"
    sample.domestic.fix.description = "IP VPN Domestic, FIX (руб.)"

    sample.domestic.fix.silver.name = "silver"
    sample.domestic.fix.silver.dimension = "RUR"
    sample.domestic.fix.silver.value = Money.parse("RUR 111500")

    sample.domestic.fix.gold.name = "gold"
    sample.domestic.fix.gold.dimension = "RUR"
    sample.domestic.fix.gold.value = Money.parse("RUR 145800")

    sample.domestic.fix.platinum.name = "platinum"
    sample.domestic.fix.platinum.dimension = "RUR"
    sample.domestic.fix.platinum.value = Money.parse("RUR 189500")
    // domestic ubb
    sample.domestic.ubb.caption = "IP VPN Domestic, UBB (руб.)"
    sample.domestic.ubb.description = "IP VPN Domestic, UBB (руб.)"
    // domestic ubb mcc
    sample.domestic.ubb.mcc.caption = "МСС, руб."
    sample.domestic.ubb.mcc.description = "mcc - минимальная сумма счета"
    // domestic ubb mcc silver
    sample.domestic.ubb.mcc.silver.name = "silver"
    sample.domestic.ubb.mcc.silver.dimension = "RUR"
    sample.domestic.ubb.mcc.silver.value = Money.parse("RUR 20100")
    // domestic ubb mcc gold
    sample.domestic.ubb.mcc.gold.name = "gold"
    sample.domestic.ubb.mcc.gold.dimension = "RUR"
    sample.domestic.ubb.mcc.gold.value = Money.parse("RUR 26300")
    // domestic ubb mcc platinum
    sample.domestic.ubb.mcc.platinum.name = "platinum"
    sample.domestic.ubb.mcc.platinum.dimension = "RUR"
    sample.domestic.ubb.mcc.platinum.value = Money.parse("RUR 34200")
    // domestic ubb colors
    sample.domestic.ubb.colors.caption = "Стоимость 1 Мб исходящего трафика по классам сервиса"
    sample.domestic.ubb.colors.description = "Стоимость 1 Мб исходящего трафика по классам сервиса"
    sample.domestic.ubb.colors.data3.dimension = "RUR"
    sample.domestic.ubb.colors.data3.value = Money.parse("RUR 1.23")
    sample.domestic.ubb.colors.data2.dimension = "RUR"
    sample.domestic.ubb.colors.data2.value = Money.parse("RUR 1.42")
    sample.domestic.ubb.colors.data1.dimension = "RUR"
    sample.domestic.ubb.colors.data1.value = Money.parse("RUR 1.67")
    sample.domestic.ubb.colors.voice.dimension = "RUR"
    sample.domestic.ubb.colors.voice.value = Money.parse("RUR 1.85")
    sample.domestic.ubb.colors.video.dimension = "RUR"
    sample.domestic.ubb.colors.video.value = Money.parse("RUR 1.91")

    // okrug
    sample.okrug.caption = "okrug область покрытия"
    sample.okrug.description = "okrug область покрытия"

    sample.okrug.fix.caption = "IP VPN Okrug, FIX (руб.)"
    sample.okrug.fix.description = "IP VPN Okrug, FIX (руб.)"

    sample.okrug.fix.silver.name = "silver"
    sample.okrug.fix.silver.dimension = "RUR"
    sample.okrug.fix.silver.value = Money.parse("RUR 44600")

    sample.okrug.fix.gold.name = "gold"
    sample.okrug.fix.gold.dimension = "RUR"
    sample.okrug.fix.gold.value = Money.parse("RUR 58400")

    sample.okrug.fix.platinum.name = "platinum"
    sample.okrug.fix.platinum.dimension = "RUR"
    sample.okrug.fix.platinum.value = Money.parse("RUR 75800")
    // okrug ubb
    sample.okrug.ubb.caption = "IP VPN Okrug, UBB (руб.)"
    sample.okrug.ubb.description = "IP VPN Okrug, UBB (руб.)"
    // okrug ubb mcc
    sample.okrug.ubb.mcc.caption = "МСС, руб."
    sample.okrug.ubb.mcc.description = "mcc - минимальная сумма счета"
    // okrug ubb mcc silver
    sample.okrug.ubb.mcc.silver.name = "silver"
    sample.okrug.ubb.mcc.silver.dimension = "RUR"
    sample.okrug.ubb.mcc.silver.value = Money.parse("RUR 8100")
    // okrug ubb mcc gold
    sample.okrug.ubb.mcc.gold.name = "gold"
    sample.okrug.ubb.mcc.gold.dimension = "RUR"
    sample.okrug.ubb.mcc.gold.value = Money.parse("RUR 10600")
    // okrug ubb mcc platinum
    sample.okrug.ubb.mcc.platinum.name = "platinum"
    sample.okrug.ubb.mcc.platinum.dimension = "RUR"
    sample.okrug.ubb.mcc.platinum.value = Money.parse("RUR 13700")
    // okrug ubb colors
    sample.okrug.ubb.colors.caption = "Стоимость 1 Мб исходящего трафика по классам сервиса"
    sample.okrug.ubb.colors.description = "Стоимость 1 Мб исходящего трафика по классам сервиса"
    sample.okrug.ubb.colors.data3.dimension = "RUR"
    sample.okrug.ubb.colors.data3.value = Money.parse("RUR 0.5")
    sample.okrug.ubb.colors.data2.dimension = "RUR"
    sample.okrug.ubb.colors.data2.value = Money.parse("RUR 0.57")
    sample.okrug.ubb.colors.data1.dimension = "RUR"
    sample.okrug.ubb.colors.data1.value = Money.parse("RUR 0.67")
    sample.okrug.ubb.colors.voice.dimension = "RUR"
    sample.okrug.ubb.colors.voice.value = Money.parse("RUR 0.74")
    sample.okrug.ubb.colors.video.dimension = "RUR"
    sample.okrug.ubb.colors.video.value = Money.parse("RUR 0.77")

    // local
    sample.local.caption = "local область покрытия"
    sample.local.description = "local область покрытия"

    sample.local.fix.caption = "IP VPN Local, FIX (руб.)"
    sample.local.fix.description = "IP VPN Local, FIX (руб.)"

    sample.local.fix.silver.name = "silver"
    sample.local.fix.silver.dimension = "RUR"
    sample.local.fix.silver.value = Money.parse("RUR 3300")

    sample.local.fix.gold.name = "gold"
    sample.local.fix.gold.dimension = "RUR"
    sample.local.fix.gold.value = Money.parse("RUR 4400")

    sample.local.fix.platinum.name = "platinum"
    sample.local.fix.platinum.dimension = "RUR"
    sample.local.fix.platinum.value = Money.parse("RUR 5600")
    // local ubb
    sample.local.ubb.caption = "IP VPN Local, UBB (руб.)"
    sample.local.ubb.description = "IP VPN Local, UBB (руб.)"
    // local ubb mcc
    sample.local.ubb.mcc.caption = "МСС, руб."
    sample.local.ubb.mcc.description = "mcc - минимальная сумма счета"
    // local ubb mcc silver
    sample.local.ubb.mcc.silver.name = "silver"
    sample.local.ubb.mcc.silver.dimension = "RUR"
    sample.local.ubb.mcc.silver.value = Money.parse("RUR 700")
    // local ubb mcc gold
    sample.local.ubb.mcc.gold.name = "gold"
    sample.local.ubb.mcc.gold.dimension = "RUR"
    sample.local.ubb.mcc.gold.value = Money.parse("RUR 900")
    // local ubb mcc platinum
    sample.local.ubb.mcc.platinum.name = "platinum"
    sample.local.ubb.mcc.platinum.dimension = "RUR"
    sample.local.ubb.mcc.platinum.value = Money.parse("RUR 1200")
    // local ubb colors
    sample.local.ubb.colors.caption = "Стоимость 1 Мб исходящего трафика по классам сервиса"
    sample.local.ubb.colors.description = "Стоимость 1 Мб исходящего трафика по классам сервиса"
    sample.local.ubb.colors.data3.dimension = "RUR"
    sample.local.ubb.colors.data3.value = Money.parse("RUR 0.18")
    sample.local.ubb.colors.data2.dimension = "RUR"
    sample.local.ubb.colors.data2.value = Money.parse("RUR 0.2")
    sample.local.ubb.colors.data1.dimension = "RUR"
    sample.local.ubb.colors.data1.value = Money.parse("RUR 0.24")
    sample.local.ubb.colors.voice.dimension = "RUR"
    sample.local.ubb.colors.voice.value = Money.parse("RUR 0.26")
    sample.local.ubb.colors.video.dimension = "RUR"
    sample.local.ubb.colors.video.value = Money.parse("RUR 0.27")
  }
  @Test
  void testConvert() {
    DBObject converted = new BusinessVpnTariffsWriteConverter().convert(sample)
    // проверка методом пристального разглядывания

//    JSONObject obj = null
//    //println converted.toString()
//    try {
//      obj = JSONObject.fromObject(jsonStr)
//      println obj.toString()
//    }catch (net.sf.json.JSONException e){
//      e.printStackTrace()
//    }
    // JSONAssert.assertEquals(converted.toString(), obj);
    //JSONAssert.assertJsonEquals(converted.toString(), obj);
  }
}
