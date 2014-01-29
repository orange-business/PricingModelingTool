package com.orange.ru.domain.product.json

import com.orange.ru.domain.Scenario

public interface ScenarioSerializer {
  String serialize(Scenario scenario)
}