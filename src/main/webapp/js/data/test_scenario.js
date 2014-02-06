"use strict";

define(['data/test_02_raw', 'models/ScenarioModel'], function (rawData, ScenarioModel) {
//   var scenario = new ScenarioModel(rawData);

  var scenario = new ScenarioModel();
  scenario.fetch();

  return scenario;
});