package com.orange.ru.service;

import com.orange.ru.domain.product.Product;

public interface ProductService {
  public Product findById(Long id);
}