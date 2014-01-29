package com.orange.ru.mongodb.repositories;

import com.orange.ru.core.exception.ProductConfigurationException;
import com.orange.ru.domain.product.BusinessVpn;
import com.orange.ru.domain.product.Product;
import com.orange.ru.domain.product.ProductItem;
import com.orange.ru.domain.product.enums.ProductItemType;
import com.orange.ru.mongodb.reference.Hour;
import com.orange.ru.mongodb.reference.installation.Installation;
import org.joda.money.BigMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * .
 * User: Зайнуллин Радик
 */
@Repository("hourRepository")
public class HourRepositoryImpl implements HourRepository{
  private final MongoOperations operations;
  @Autowired
  public HourRepositoryImpl(MongoOperations operations) {
    Assert.notNull(operations);
    this.operations = operations;
  }
  @Override
  public Hour findByCost(String cost) {
    Query query = query(where("cost").is(cost));
    return operations.findOne(query, Hour.class);
  }

  @Override
  public BigMoney getMoneyByCost(String cost) {
    return findByCost(cost).getValue();
  }

  @Override
  public BigMoney calculateByCostAndProductItemType(String cost, ProductItem item) {
    BigMoney money = null;
    Product product = item.getProduct();
    if (product instanceof BusinessVpn){
      money = findByCost(cost).getValue();
      if (item.getType() == ProductItemType.NEW) money = money.multipliedBy(2);
    }
    return money;
  }
}