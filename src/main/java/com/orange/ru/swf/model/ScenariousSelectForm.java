package com.orange.ru.swf.model;

import java.io.Serializable;

/**
 * Форма для выбора сценария из списка сценариев, приписанных пользователю.
 * Пока работаем в тестовом режиме, выдаются все существующие сценарии из которых можно выбрать один сценарий.
 */
public class ScenariousSelectForm implements Serializable {
  private Long selectedScenarioID;

  public Long getSelectedScenario() {
    return selectedScenarioID;
  }

  public void setSelectedScenario(Long id) {
    this.selectedScenarioID = id;
  }
}