package groovy_tools.business_vpn

@Grab("com.gmongo:gmongo:1.2")
import com.gmongo.GMongo
import com.mongodb.BasicDBList
import com.mongodb.DBCollection
import com.mongodb.DBObject
import com.mongodb.util.JSON
import com.orange.ru.mongodb.coefficient.Coefficient
import com.orange.ru.mongodb.coefficient.CoefficientItem
import com.orange.ru.mongodb.coefficient.Speed
import com.orange.ru.mongodb.converter.CoefficientWriteConverter

def GMongo gmongo = new GMongo("localhost:27017")
def db = gmongo.getDB("pricing")

db.Coefficients.drop()
DBCollection collection = db.getCollection("Coefficients")
def json = new File("Coefficient1.json").getText()
BasicDBList dbList = (BasicDBList) JSON.parse(json)
CoefficientWriteConverter converter = new CoefficientWriteConverter()

for( Iterator<Object> it = dbList.iterator(); it.hasNext(); ){
  DBObject curr = it.next()
  Coefficient coefficient = new Coefficient()
  coefficient.caption = "Коэффициенты расчета в зависимости от скорости и города"
  coefficient.description = "Коэффициенты расчета в зависимости от скорости и города"
  coefficient.source = "Копия BVPN for PT 08042013.xlsx"

  coefficient.speed.caption = "скорость подключения"
  coefficient.speed.value = curr.get("velocity")
  coefficient.speed.dimension = "Кбит/с"

  CoefficientItem item = new CoefficientItem()
  // Moscow
  item.townNames.add("Москва")
  item.townNames.add("Moscow")
  item.value = curr.get("Moscow")
  coefficient.coefficient.put("moscow", item)
  // Petersburg
  item = new CoefficientItem()
  item.townNames.add("Петербург")
  item.townNames.add("Petersburg")
  item.value = curr.get("Petersburg")
  coefficient.coefficient.put("petersburg", item)
  // other
  item = new CoefficientItem()
  item.townNames.add("другие")
  item.townNames.add("others")
  item.value = curr.get("others")
  coefficient.coefficient.put("other", item)

  collection.insert(converter.convert(coefficient))
}
println collection.count