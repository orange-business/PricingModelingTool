package com.orange.ru.service;

import com.orange.ru.domain.*;
import com.orange.ru.domain.product.ProductItem;

import java.util.List;

public interface ScenarioService {
  List<Scenario> findByOpportunity(Long opportunityId);
  Scenario findById(Long id);
}