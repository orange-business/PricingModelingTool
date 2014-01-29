package com.orange.ru.domain.product.json

import com.orange.ru.domain.Scenario

public interface ScenarioDeserializer {
  Scenario deserialize(String json)
}