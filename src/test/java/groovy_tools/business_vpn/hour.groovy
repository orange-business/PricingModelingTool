package groovy_tools.business_vpn

@Grab("com.gmongo:gmongo:1.2")
import com.gmongo.GMongo
import com.mongodb.DBCollection
import com.mongodb.DBObject
import com.mongodb.util.JSON

def GMongo gmongo = new GMongo("localhost:27017")
def db = gmongo.getDB("pricing")

db.hour.drop()
DBCollection collection = db.getCollection("hour")
def json = new File("hour.json").getText()
DBObject dbObject = (DBObject) JSON.parse(json)
dbObject.each{
  collection.insert(it)
}