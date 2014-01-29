package com.orange.ru.service;

import com.orange.ru.domain.Customer;
import com.orange.ru.domain.Port;
import com.orange.ru.domain.Site;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("portService")
@Repository
@Transactional(readOnly = true)
public class PortServiceImpl implements PortService {
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }
  private EntityManager em;

  public Port findById(long id) {
    return em.find(Port.class, id);
  }
}