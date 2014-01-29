package com.orange.ru.service;

import com.orange.ru.domain.Customer;
import com.orange.ru.domain.Site;

import java.util.List;

public interface SiteService {
  public Site findById(long id);
  public List<Site> findByCustomer(Customer customer);
  public List<Site> findByCustomer(Long customerId);
}
