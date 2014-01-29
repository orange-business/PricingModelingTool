package com.orange.ru.service;

import com.orange.ru.domain.Customer;

import java.util.List;

public interface CustomerService {
  public List<Customer> findAll();
  public Customer findById(long id);
  public Long persist(String official);
}