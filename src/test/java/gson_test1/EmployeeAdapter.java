package gson_test1;

import com.google.gson.*;
import java.lang.reflect.Type;

public class EmployeeAdapter implements JsonSerializer<Employee>, JsonDeserializer<Employee> {
  public JsonElement serialize(Employee employee, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject result = new JsonObject();
    result.add("userid", context.serialize(employee.getUserid(), String.class));
    result.add("startDate", context.serialize(employee.getStartDate(), long.class));
    if (employee instanceof Manager) {
      result.add("minions", context.serialize(((Manager) employee).minions, Employee[].class));
      if (employee instanceof CEO) result.add("assistant", context.serialize(((CEO) employee).assistant, Employee.class));
    }
    return result;
  }
  public Employee deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject object = json.getAsJsonObject();
    Employee result = null;

    // if the employee has an assistant, she must be the CEO
    JsonElement assistant = object.get("assistant");
    if (assistant != null) {
      result = new CEO();
      ((CEO) result).assistant = context.deserialize(assistant, Employee.class);
    }
    // only managers have minions
    JsonElement minons = object.get("minions");
    if (minons != null) {
      if (result == null) result = new Manager();
      ((Manager) result).minions = context.deserialize(minons, Employee[].class);
    }

    if (result == null) result = new Employee();

    result.setUserid((String)context.deserialize(object.get("userid"), String.class));
    result.setStartDate(context.<Long>deserialize(object.get("startDate"), long.class));
    return result;
  }
}