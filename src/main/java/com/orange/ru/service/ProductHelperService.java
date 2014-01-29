package com.orange.ru.service;

import java.util.List;

public interface ProductHelperService {
  public List<Integer> getAgreementPeriods();
  public List<String> getAllProvisionNames();
  public String getProvisionCodeByName(String provisionName);
}