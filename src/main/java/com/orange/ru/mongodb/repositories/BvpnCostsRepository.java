package com.orange.ru.mongodb.repositories;

import com.orange.ru.domain.Port;
import com.orange.ru.domain.product.ProductItem;
import com.orange.ru.domain.product.enums.PortCoverage;
import com.orange.ru.mongodb.reference.bvpncosts.BusinessVpnCosts;
import org.joda.money.BigMoney;
import org.springframework.data.repository.Repository;
import java.util.List;

public interface BvpnCostsRepository extends Repository<BusinessVpnCosts, Long> {
  BusinessVpnCosts findOne(Long id);
  BusinessVpnCosts findByTown(String town);
  BusinessVpnCosts save(BusinessVpnCosts cost);
  void delete(BusinessVpnCosts cost);
  List<String> getAllTowns();
  String getTownByName(String name);
  BigMoney getMoneyByTownAndCoverage(String town, PortCoverage coverage);
  BigMoney getMoneyByProductItem(ProductItem item);
}
