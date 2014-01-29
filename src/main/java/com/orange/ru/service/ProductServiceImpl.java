package com.orange.ru.service;

import com.orange.ru.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("productService")
@Repository
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
  @Autowired ApplicationContext ctx;

  private EntityManager em;
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }

  public Product findById(Long id){
    return em.find(Product.class, id);
  }
}