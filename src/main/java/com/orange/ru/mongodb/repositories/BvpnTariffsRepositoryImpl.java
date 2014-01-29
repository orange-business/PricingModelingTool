package com.orange.ru.mongodb.repositories;

import com.orange.ru.domain.product.BusinessVpn;
import com.orange.ru.mongodb.reference.bvpntariffs.BusinessVpnTariffs;
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
 * Date: 24.07.13
 */
@Repository("bvpnTariffsRepository")
public class BvpnTariffsRepositoryImpl implements BvpnTariffsRepository {
  private final MongoOperations operations;
  @Autowired
  public BvpnTariffsRepositoryImpl(MongoOperations operations) {
    Assert.notNull(operations);
    this.operations = operations;
  }
  @Override
  public BusinessVpnTariffs findByTown(String town) {
    Query query = query(where("town").is(town));
    return operations.findOne(query, BusinessVpnTariffs.class);
  }

  @Override
  public BigMoney getFixValueByProduct(BusinessVpn product) {
    BusinessVpnTariffs tariffs = findByTown(product.getTown());
    BigMoney money = tariffs.getFixValue(product.getPort().getCoverage(), product.getPort().getType());
    return money;
  }
}
