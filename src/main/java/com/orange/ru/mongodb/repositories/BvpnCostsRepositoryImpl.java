package com.orange.ru.mongodb.repositories;

import com.orange.ru.domain.product.BusinessVpn;
import com.orange.ru.domain.product.ProductItem;
import com.orange.ru.domain.product.enums.PortCoverage;
import com.orange.ru.mongodb.reference.bvpncosts.BusinessVpnCosts;
import com.orange.ru.mongodb.reference.bvpncosts.CostChannel;
import org.joda.money.BigMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import java.util.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.query.Query.*;
/**
 * Репозиторий.
 * User: Зайнуллин Радик
 */
@Repository("bvpnCostsRepository")
public class BvpnCostsRepositoryImpl implements BvpnCostsRepository {
  private final MongoOperations operations;
  @Autowired
  public BvpnCostsRepositoryImpl(MongoOperations operations) {
    Assert.notNull(operations);
    this.operations = operations;
  }
  @Override
  public BusinessVpnCosts findOne(Long id) {
    Query query = query(where("id").is(id));
    return operations.findOne(query, BusinessVpnCosts.class);
  }
  @Override
  public BusinessVpnCosts findByTown(String town) {
    Query query = query(where("town").is(town));
    return operations.findOne(query, BusinessVpnCosts.class);
  }
  @Override
  public BusinessVpnCosts save(BusinessVpnCosts cost) {
    operations.save(cost);
    return cost;
  }
  @Override
  public void delete(BusinessVpnCosts cost) {
    operations.remove(cost);
  }
  @Override
  public List<String> getAllTowns() {
    List<BusinessVpnCosts> list = operations.findAll(BusinessVpnCosts.class);
    List<String> out = new ArrayList<String>();
    for (BusinessVpnCosts curr: list) out.add(curr.getTown());
    return out;
  }
  @Override
  public String getTownByName(String name) {
    List<String> towns = getAllTowns();
    for (String curr: towns) if (curr.equalsIgnoreCase(name.trim())) return curr;
    return null;
  }
  @Override
  public BigMoney getMoneyByTownAndCoverage(String town, PortCoverage coverage) {
    CostChannel costCoverage = findByTown(town).getCostsChannel().get(coverage.getCode());
    return costCoverage.getValue();
  }
  @Override
  public BigMoney getMoneyByProductItem(ProductItem item) {
    BusinessVpn product =  (BusinessVpn) item.getProduct();
    return getMoneyByTownAndCoverage(product.getTown(), product.getPort().getCoverage());
  }
}