package com.orange.ru.service;

import com.orange.ru.domain.product.Identification;
import java.util.*;

public interface IdentificationService {
  public Identification get(String namespace, String code);
  public List<Identification> getAll();
  public Map<String, Identification> getByNS(String ns);
}