package gson_test1;

import com.google.gson.*;
import java.lang.reflect.Type;

public class ManagerAdapter implements JsonSerializer<Manager>, JsonDeserializer<Manager> {
  public Manager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
    Manager result = new Manager();
    result.setUserid(json.getAsString());
    return result;
  }
  public JsonElement serialize(Manager src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(src.getUserid());
  }
}
