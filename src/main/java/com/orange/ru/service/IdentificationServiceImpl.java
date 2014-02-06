package com.orange.ru.service;

import com.orange.ru.domain.product.*;
import com.orange.ru.domain.product.Identification;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service("identificationService")
public class IdentificationServiceImpl implements IdentificationService {
  private EntityManager em;
  @PersistenceContext
  public void setEntityManager(EntityManager em) { this.em = em; }
  @Override
  public Identification get(String namespace, String code) {
    Identification identification = null;
    try {
      identification = (Identification) em.createNamedQuery("Identification.findByCode").setParameter(1, namespace).setParameter(2, code).getResultList().get(0);
    } catch (Exception io){ return null; }
    return identification;
  }
  @Override
  public List<Identification> getAll() {
    return (List<Identification>) em.createNamedQuery("Identification.findAll").getResultList();
  }

  @Override
  public Map<String, Identification> getByNS(String ns) {
    Map<String, Identification> map = new HashMap<>();
    Iterator iterator = ((List<Identification>) em.createNamedQuery("Identification.findByNS").setParameter(1, ns).getResultList()).iterator();
    while (iterator.hasNext()){
      Identification curr = (Identification) iterator.next();
      map.put(curr.getCode(),curr);
    }
    return map;
  }
  @Override
  public Identification getByProductCode(String productCode, String identificationCode) {
    Identification identification = null;
    if (productCode.equals(BusinessVpn.getClassCode())) identification = get("bvpn", identificationCode);
    if (productCode.equals(AccessLines.getClassCode())) identification = get("lines", identificationCode);
    if (identification == null) identification = get("common", identificationCode);
    return identification;
  }
}
