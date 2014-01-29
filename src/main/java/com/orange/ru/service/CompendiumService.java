package com.orange.ru.service;

import org.json.simple.JSONArray;

import java.util.List;

/**
 *
 * User: radik
 * Date: 11.06.13
 */
public interface CompendiumService {
  public JSONArray retrieveUnitCostPerHour() throws Exception;
  public List<Integer> getSpeedValues();
  List<String> getTarifficationPlanValues();
}
