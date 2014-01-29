package com.orange.ru.service;

import com.orange.ru.domain.Customer;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("customerService")
@Repository
public class CustomerServiceImpl implements CustomerService {
  private EntityManager em;
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }
  public List<Customer> findAll() {
    return em.createQuery("select c from Customer c").getResultList();
  }
  public Customer findById(long id) {
    return em.find(Customer.class, id);
  }
  @Override
  @Transactional(readOnly = false)
  public Long persist(String official) {
    Customer customer = new Customer();
    customer.setOfficial(official);
    em.persist(customer);
    em.flush();
    return customer.getId();
  }
}
