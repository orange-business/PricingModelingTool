package gson_test1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Test that the hierarchy adapter works when subtypes are used. */
public final class TypeHierarchyAdapterTest extends TestCase {
  @Test
  public void testTypeHierarchy() throws URISyntaxException, IOException {
    Manager andy = new Manager();
    andy.setUserid("andy");
    andy.setStartDate(2005);
    andy.minions = new Employee[]{ new Employee("inder", 2007), new Employee("joel", 2006), new Employee("jesse", 2006)};

    CEO eric = new CEO();
    eric.setUserid("eric");
    eric.setStartDate(2001);
    eric.assistant = new Employee("jerome", 2006);

    eric.minions = new Employee[]{ new Employee("larry", 1998), new Employee("sergey", 1998), andy };

    Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Employee.class, new EmployeeAdapter())
                                 .setPrettyPrinting().create();

    Company company = new Company();
    company.ceo = eric;

    String json = gson.toJson(company, Company.class);

    Path resPath = Paths.get(TypeHierarchyAdapterTest.class.getResource("../test.json").toURI());
    String text = new String(Files.readAllBytes(resPath), "UTF8");

    assertEquals (text, json);

    Company copied = gson.fromJson(json, Company.class);
    assertEquals(json, gson.toJson(copied, Company.class));
    assertEquals(copied.ceo.getUserid(), company.ceo.getUserid());
    assertEquals(copied.ceo.assistant.getUserid(), company.ceo.assistant.getUserid());
    assertEquals(copied.ceo.minions[0].getUserid(), company.ceo.minions[0].getUserid());
    assertEquals(copied.ceo.minions[1].getUserid(), company.ceo.minions[1].getUserid());
    assertEquals(copied.ceo.minions[2].getUserid(), company.ceo.minions[2].getUserid());
    assertEquals(((Manager) copied.ceo.minions[2]).minions[0].getUserid(), ((Manager) company.ceo.minions[2]).minions[0].getUserid());
    assertEquals(((Manager) copied.ceo.minions[2]).minions[1].getUserid(), ((Manager) company.ceo.minions[2]).minions[1].getUserid());
  }
  @Test
  public void testRegisterSuperTypeFirst() {
    Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Employee.class, new EmployeeAdapter())
                                 .registerTypeHierarchyAdapter(Manager.class, new ManagerAdapter()).create();
    Manager manager = new Manager();
    manager.setUserid("inder");

    String json = gson.toJson(manager, Manager.class);
    assertEquals("\"inder\"", json);
    Manager copied = gson.fromJson(json, Manager.class);
    assertEquals(manager.getUserid(), copied.getUserid());
  }
  @Test(expected=AssertionFailedError.class)
  public void testRegisterSubTypeFirstNotAllowed() {
    Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Manager.class, new ManagerAdapter())
                                   .registerTypeHierarchyAdapter(Employee.class, new EmployeeAdapter()).create();
    fail();
  }
}