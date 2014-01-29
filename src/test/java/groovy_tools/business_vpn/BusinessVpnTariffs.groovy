package groovy_tools.business_vpn
// 2;Дальневосточный;Благовещенск;C;2 048;да;67 700;88 500;115 000;12 200;16 000;20 700;1,23;1,42;1,67;1,85;1,91;27 100;35 400;46 000;4 900;6 400;8 300;0,50;0,57;0,67;0,74;0,77;3 300;4 400;5 600;700;900;1 200;0,18;0,20;0,24;0,26;0,27
@Grab("com.gmongo:gmongo:1.2")
import com.gmongo.GMongo
import com.mongodb.*
import com.mongodb.util.JSON

class Coverage { def name, caption, description, tariffications }
class Fix { def name, caption, description, ports }
class Ubb { def name, caption, description, mcc, service }
class Port { def name, value, dimension }
class Mcc { def caption, description, ports }
class Service { def caption, description, colors }
class Color { def name, value, dimension }
class BaseSpeed {def caption, description, value, dimension}

def GMongo gmongo = new GMongo("localhost:27017")
def db = gmongo.getDB("pricing")

db.BusinessVpnTariffs1.drop()
DBCollection collection = db.getCollection("BusinessVpnTariffs1")

def baseSpeed = new BaseSpeed (
  caption:'Скорость, Kbps',
  description:'Базовая скорость',
  value:2048,
  dimension:'Kbps')

new File('Tariffs.csv').eachLine { line ->
  def resultList = line.tokenize(';');
  // корень
  def coverageList = [];
  coverageList.add(
    new Coverage(
      name: 'domestic', caption: 'domestic область покрытия', description: 'domestic область покрытия',
      tariffications: [
        new Fix(
          name: "fix", caption: "IP VPN Domestic, FIX (руб.)", description: "IP VPN Domestic, FIX (руб.)",
          ports: [
            new Port(
              name: "silver",
              value: Long.valueOf(resultList[6].replaceAll("\\s*", "")),
              dimension: "RUR"
            ),
            new Port(
              name: "gold",
              value: Long.valueOf(resultList[7].replaceAll("\\s*", "")),
              dimension: "RUR"
            ),
            new Port(
              name: "platinum",
              value: Long.valueOf(resultList[8].replaceAll("\\s*", "")),
              dimension: "RUR"
            )
          ]
        ),
        new Ubb(
          name: "ubb", caption: "IP VPN Domestic, UBB (руб.)", description: "IP VPN Domestic, UBB (руб.)",
          mcc: new Mcc(
            caption: "МСС, руб.",
            description: "mcc - минимальная сумма счета",
            ports: [
              new Port(
                name: "silver",
                value: Long.valueOf(resultList[9].replaceAll("\\s*", "")),
                dimension: "RUR"
              ),
              new Port(
                name: "gold",
                value: Long.valueOf(resultList[10].replaceAll("\\s*", "")),
                dimension: "RUR"
              ),
              new Port(
                name: "platinum",
                value: Long.valueOf(resultList[11].replaceAll("\\s*", "")),
                dimension: "RUR"
              )
            ]
          ),
          service: new Service(
            caption: "Стоимость 1 Мб исходящего трафика по классам сервиса",
            description: "Стоимость 1 Мб исходящего трафика по классам сервиса",
            colors: [
              new Color(
                name: "data3",
                value: Double.valueOf(resultList[12].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "data2",
                value: Double.valueOf(resultList[13].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "data1",
                value: Double.valueOf(resultList[14].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "voice",
                value: Double.valueOf(resultList[15].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "video",
                value: Double.valueOf(resultList[16].replaceAll("\\,", ".")),
                dimension: "RUR"
              )
            ]
          )
        )
      ]
    )
  );
  coverageList.add(
    new Coverage(
      name: 'okrug', caption: 'okrug область покрытия', description: 'okrug область покрытия',
      tariffications: [
        new Fix(
          name: "fix",
          caption: "IP VPN Okrug, FIX (руб.)",
          description: "IP VPN Okrug, FIX (руб.)",
          ports: [
            new Port(
              name: "silver",
              value: resultList[17].replaceAll("\\s*", "") == "-" ? -1 : Long.valueOf(resultList[17].replaceAll("\\s*", "")),
              dimension: "RUR"
            ),
            new Port(
              name: "gold",
              value: resultList[18].replaceAll("\\s*", "") == "-" ? -1 : Long.valueOf(resultList[18].replaceAll("\\s*", "")),
              dimension: "RUR"
            ),
            new Port(
              name: "platinum",
              value: resultList[19].replaceAll("\\s*", "") == "-" ? -1 : Long.valueOf(resultList[19].replaceAll("\\s*", "")),
              dimension: "RUR"
            )
          ]
        ),
        new Ubb(
          name: "ubb", caption: "IP VPN Okrug, UBB (руб.)", description: "IP VPN Okrug, UBB (руб.)",
          mcc: new Mcc(
            caption: "МСС, руб.", description: "mcc - минимальная сумма счета",
            ports: [
              new Port(
                name: "silver",
                value: resultList[20].replaceAll("\\s*", "") == "-" ? -1 : Long.valueOf(resultList[20].replaceAll("\\s*", "")),
                dimension: "RUR"
              ),
              new Port(
                name: "gold",
                value: resultList[21].replaceAll("\\s*", "") == "-" ? -1 : Long.valueOf(resultList[21].replaceAll("\\s*", "")),
                dimension: "RUR"
              ),
              new Port(
                name: "platinum",
                value: resultList[22].replaceAll("\\s*", "") == "-" ? -1 : Long.valueOf(resultList[22].replaceAll("\\s*", "")),
                dimension: "RUR"
              )
            ]
          ),
          service: new Service(
            caption: "Стоимость 1 Мб исходящего трафика по классам сервиса",
            description: "Стоимость 1 Мб исходящего трафика по классам сервиса",
            colors: [
              new Color(
                name: "data3",
                value: resultList[23].replaceAll("\\s*", "") == "-" ? -1 : Double.valueOf(resultList[23].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "data2",
                value: resultList[24].replaceAll("\\s*", "") == "-" ? -1 : Double.valueOf(resultList[24].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "data1",
                value: resultList[25].replaceAll("\\s*", "") == "-" ? -1 : Double.valueOf(resultList[25].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "voice",
                value: resultList[26].replaceAll("\\s*", "") == "-" ? -1 : Double.valueOf(resultList[26].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "video",
                value: resultList[27].replaceAll("\\s*", "") == "-" ? -1 : Double.valueOf(resultList[27].replaceAll("\\,", ".")),
                dimension: "RUR"
              )
            ]
          )
        )
      ]
    )
  );
  coverageList.add(
    new Coverage(
      name: 'local', caption: 'local область покрытия', description: 'local область покрытия',
      tariffications: [
        new Fix(
          name: "fix", caption: "IP VPN Local, FIX (руб.)", description: "IP VPN Local, FIX (руб.)",
          ports: [
            new Port(
              name: "silver",
              value: Long.valueOf(resultList[28].replaceAll("\\s*", "")),
              dimension: "RUR"
            ),
            new Port(
              name: "gold",
              value: Long.valueOf(resultList[29].replaceAll("\\s*", "")),
              dimension: "RUR"
            ),
            new Port(
              name: "platinum",
              value: Long.valueOf(resultList[30].replaceAll("\\s*", "")),
              dimension: "RUR"
            )
          ]
        ),
        new Ubb(
          name: "ubb", caption: "IP VPN Local, UBB (руб.)", description: "IP VPN Local, UBB (руб.)",
          mcc: new Mcc(
            caption: "МСС, руб.", description: "mcc - минимальная сумма счета",
            ports: [
              new Port(
                name: "silver",
                value: Long.valueOf(resultList[31].replaceAll("\\s*", "")),
                dimension: "RUR"
              ),
              new Port(
                name: "gold",
                value: Long.valueOf(resultList[32].replaceAll("\\s*", "")),
                dimension: "RUR"
              ),
              new Port(
                name: "platinum",
                value: Long.valueOf(resultList[33].replaceAll("\\s*", "")),
                dimension: "RUR"
              )
            ]
          ),
          service: new Service(
            caption: "Стоимость 1 Мб исходящего трафика по классам сервиса",
            description: "Стоимость 1 Мб исходящего трафика по классам сервиса",
            colors: [
              new Color(
                name: "data3",
                value: Double.valueOf(resultList[34].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "data2",
                value: Double.valueOf(resultList[35].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "data1",
                value: Double.valueOf(resultList[36].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "voice",
                value: Double.valueOf(resultList[37].replaceAll("\\,", ".")),
                dimension: "RUR"
              ),
              new Color(
                name: "video",
                value: Double.valueOf(resultList[38].replaceAll("\\,", ".")),
                dimension: "RUR"
              )
            ]
          )
        )
      ]
    )
  );
  def jsonBuilder = new groovy.json.JsonBuilder(
    town: resultList[2],
    caption: "IPVPN Availability Matrix",
    description: "IPVPN Availability Matrix",
    source: "BVPN Tariffs for PT 29052013.xlsx",
    base: baseSpeed,
    coverage: coverageList
  );
  collection.insert((DBObject) JSON.parse(jsonBuilder.toString()));
}
println collection.count