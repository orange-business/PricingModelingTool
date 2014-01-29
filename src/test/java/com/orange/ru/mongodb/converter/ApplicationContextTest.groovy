package com.orange.ru.mongodb.converter

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ["classpath:config/web-application-config.xml"])
@TestExecutionListeners([DependencyInjectionTestExecutionListener.class])
public class ApplicationContextTest extends GroovyTestCase implements ApplicationContextAware {

  ApplicationContext applicationContext

  ApplicationContext getApplicationContext(){
    return applicationContext;
  }

  @Test
  void testContext() {
    assert applicationContext != null
    assert applicationContext.containsBean("dataSource")
  }
}