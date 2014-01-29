package com.orange.ru.service;

import com.orange.ru.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.*;

@Service("siteService")
@Repository
@Transactional(readOnly = true)
public class SiteServiceImpl implements SiteService {
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }
  private EntityManager em;

  public Site findById(long id) {
    return em.find(Site.class, id);
  }
  public List<Site> findByCustomer(Customer customer) {
    List<Site> sites = em.createQuery("select st from Site st where st.customer = ?1").setParameter(1, customer).getResultList();
    return sites;
  }
  public List<Site> findByCustomer(Long customerId) {
    Customer customer = em.find(Customer.class, customerId);
    return findByCustomer(customer);
  }
}