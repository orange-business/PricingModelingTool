package groovy_tools.business_vpn
//IPVPN Availability Matrix - Debug.xls - Кост IP VPN на Кбит, Кост каналов на Кбит
// Абакан;Abakan;C;есть;Новосибирск;есть;есть;PoP;Cisco;СР;Новосибирск;Республика Хакасия;СФО;0,00;;2048;7,60;5,34;;2,94;2,07;

@Grab("com.gmongo:gmongo:1.2")
import com.gmongo.GMongo
import com.mongodb.DBCollection
import com.mongodb.DBObject
import com.mongodb.util.JSON

class MyCost { def name, value, dimension, description }

def GMongo gmongo = new GMongo("localhost:27017")
def db = gmongo.getDB("pricing")

db.BusinessVpnCosts1.drop()
DBCollection collection = db.getCollection("BusinessVpnCosts1")

def relative = 12

new File('Tariffs.csv').eachLine { line ->
  def resultList = line.tokenize(';');
  def jsonBuilder = new groovy.json.JsonBuilder();

  // Кост IP VPN на Кбит
  def costIpVpnList = []
  if (!(resultList[relative + 7].trim().matches("")|resultList[relative + 7].trim().matches("-"))){
    def cost1 = new MyCost(
        name: 'domestic',
        value: Double.parseDouble(resultList[relative + 7].replaceAll("\\s+","").replaceAll(",",".")),
        dimension:  'RUR',
        description: 'Кост IP VPN на Кбит'
    )
    costIpVpnList.add(cost1)
  }
  if (!(resultList[relative + 8]==null||resultList[relative + 8].trim().matches("-"))){
    def cost2 = new MyCost(
        name: 'okrug',
        value: Double.parseDouble(resultList[relative + 8].replaceAll("\\s+","").replaceAll(",",".")),
        dimension:  'RUR',
        description: 'Кост IP VPN на Кбит'
    )
    costIpVpnList.add(cost2)
  }
  def cost3 = new MyCost(
      name: 'local',
      value: 0.00,
      dimension:  'RUR',
      description: 'Кост IP VPN на Кбит'
  )
  costIpVpnList.add(cost3)

  // Кост каналов на Кбит
  def costChannelList = []
  if (!(resultList[relative + 5].trim().matches("")|resultList[relative + 5].trim().matches("-"))){
    def cost1 = new MyCost(
        name: 'domestic',
        value: Double.parseDouble(resultList[relative + 5].replaceAll("\\s+","").replaceAll(",",".")),
        dimension:  'RUR',
        description: 'Кост каналов на Кбит'
    )
    costChannelList.add(cost1)
  }

  if (!(resultList[relative + 6]==null||resultList[relative + 6].trim().matches("-"))){
    def cost2 = new MyCost(
        name: 'okrug',
        value: Double.parseDouble(resultList[relative + 6].replaceAll("\\s+","").replaceAll(",",".")),
        dimension:  'RUR',
        description: 'Кост каналов на Кбит'
    )
    costChannelList.add(cost2)
  }
  def costChannel3 = new MyCost(
      name: 'local',
      value: 0.00,
      dimension:  'RUR',
      description: 'Кост каналов на Кбит'
  )
  costChannelList.add(costChannel3)

  jsonBuilder(
    town: resultList[2],
    costsChannel: costChannelList,
    costsIpVpn: costIpVpnList,
    info: "IPVPN Availability Matrix - Debug.xls"
  )
  DBObject dbObject = (DBObject) JSON.parse(jsonBuilder.toString())
  collection.insert(dbObject)
}
println collection.count