package com.orange.ru.domain.product.renew

import com.orange.ru.domain.Scenario

interface HandleScenario {
  def calculate(Scenario scenario)
}