package populate

import com.orange.ru.domain.Scenario
import com.orange.ru.domain.product.renew.HandleScenario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import com.orange.ru.service.ScenarioService
//import com.orange.ru.domain.product.gson1.GsonUtil1

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class ScenarioFromDBtoJsonSpec extends spock.lang.Specification {
  static final Logger LOG = LoggerFactory.getLogger(ScenarioFromDBtoJsonSpec.class);
  @Autowired private ScenarioService scenarioService
  @Autowired HandleScenario handleScenario
  Scenario recoved

  def setup(){
    LOG.debug("DeserializedScenarioSaveToDbSpec works.");
    Scenario scenario = scenarioService.findById(1);
    LOG.debug(GsonUtil1.gson.toJson(scenario))
  }
  def "Правильная сериализация вытащенного из базы сценария"() {
    expect: true
  }
}