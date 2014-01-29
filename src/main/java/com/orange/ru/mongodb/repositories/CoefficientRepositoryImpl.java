package com.orange.ru.mongodb.repositories;

import com.orange.ru.domain.product.BusinessVpn;
import com.orange.ru.mongodb.coefficient.Coefficient;
import com.orange.ru.mongodb.coefficient.CoefficientItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 *
 * User: Зайнуллин Радик
 * Date: 02.08.13
 */
@Repository("coefficientRepository")
public class CoefficientRepositoryImpl implements CoefficientRepository {
  private final MongoOperations operations;
  @Autowired
  public CoefficientRepositoryImpl(MongoOperations operations) {
    Assert.notNull(operations);
    this.operations = operations;
  }

  @Override
  public List<Coefficient> findAll() {
    return operations.findAll(Coefficient.class);
  }

  @Override
  public Coefficient findBySpeed(Integer speed) {
    Query query = new Query(Criteria.where("speed.value").is(speed));
    return operations.findOne(query, Coefficient.class);
  }
  @Override
  public void save(Coefficient in) {
    operations.save(in);
  }

  @Override
  public double findBySpeedAndTown(Integer speed, String town) {
    Map<String, CoefficientItem> map = findBySpeed(speed).getCoefficient();
    String key = "other";
    for (Map.Entry<String, CoefficientItem> entry : map.entrySet()){
      if (entry.getValue().checkName(town)) key = entry.getKey();
    }
    CoefficientItem coefficientItem = map.get(key);
    return coefficientItem.getValue();
  }
}