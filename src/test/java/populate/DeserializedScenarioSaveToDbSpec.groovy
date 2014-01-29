package populate

import com.orange.ru.domain.*
import com.orange.ru.domain.product.renew.HandleScenario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import com.orange.ru.domain.product.gson.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@Transactional
class DeserializedScenarioSaveToDbSpec extends spock.lang.Specification {
  static final Logger LOG = LoggerFactory.getLogger(DeserializedScenarioSaveToDbSpec.class)

  @PersistenceContext EntityManager em;
  @Autowired HandleScenario handleScenario
  Scenario recoved

  def setup(){
    LOG.debug("DeserializedScenarioSaveToDbSpec works.");
    // String json = new File('src/test/java/populate/scenario01.json').getText('UTF-8')
    String json = new File('src/test/java/populate/scenarioBvpn01.json').getText('UTF-8')

    recoved = GsonUtil.gson.fromJson(json, Scenario.class)

    handleScenario.calculate recoved
  }
  @Transactional
  @Rollback(false)
  def "Сохранение востановленного scenario в базе"() {
    // проверка
    em.persist(recoved)
    em.flush()
    expect: em
  }
}