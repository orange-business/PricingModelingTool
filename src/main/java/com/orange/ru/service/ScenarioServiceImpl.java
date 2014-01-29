package com.orange.ru.service;

import com.orange.ru.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service("scenarioService")
@Repository
@Transactional(readOnly = true)
public class ScenarioServiceImpl implements ScenarioService {
  private EntityManager em;
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }

  @Override
  public List<Scenario> findByOpportunity(Long opportunityId) {
    Opportunity opportunity = em.find(Opportunity.class, opportunityId);

    List<Scenario> scenarios = em.createQuery("select c.scenarios from Opportunity c where c=?1").setParameter(1, opportunity).getResultList();
    return scenarios;
  }
  @Override
  public Scenario findById(Long id) {
    return (Scenario)em.createQuery("select s from Scenario s where s.id=?1").setParameter(1, id).getResultList().get(0);
    //return (Scenario) em.createNamedQuery("Scenario.findById").setParameter(1, id).getSingleResult();
  }
}