package com.orange.ru.mongodb.repositories;

import com.orange.ru.domain.Opportunity;
import com.orange.ru.domain.product.BusinessVpn;
import com.orange.ru.domain.product.ProductItem;
import com.orange.ru.domain.product.enums.ProductItemType;
import com.orange.ru.mongodb.reference.installation.Installation;
import org.joda.money.BigMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 *
 * User: Зайнуллин Радик
 * Date: 22.07.13
 */
@Repository("installationRepository")
public class InstallationRepositoryImpl implements InstallationRepository {
  private final MongoOperations operations;
  @Autowired
  public InstallationRepositoryImpl(MongoOperations operations) {
    Assert.notNull(operations);
    this.operations = operations;
  }
  @Override
  public Installation findOne(Long id) {
    Query query = query(where("id").is(id));
    return operations.findOne(query, Installation.class);
  }
  public Installation findByIndex(String index){
    Query query = query(where("index").is(index));
    return operations.findOne(query, Installation.class);
  }
  @Override
  public Installation findByOpportunityTypeAndContractTermAndSpeed(ProductItemType type, Integer contractTerm, Integer speed) {
    Installation installation = findByIndex(Installation.key(contractTerm, speed));
    if (!(type == ProductItemType.NEW)) {
      BigMoney money = installation.getPayment().getOnetime().multipliedBy(0.5);
      installation.getPayment().setOnetime(money);
    }
    return installation;
  }

  @Override
  public BigMoney getMoneyByItem(ProductItem item, int contractTerm) {
    Installation installation = findByOpportunityTypeAndContractTermAndSpeed(item.getType(),contractTerm, ((BusinessVpn) item.getProduct()).getPort().getSpeed());
    return installation.getPayment().getOnetime();
  }
}
