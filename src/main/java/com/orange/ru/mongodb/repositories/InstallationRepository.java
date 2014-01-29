package com.orange.ru.mongodb.repositories;

import com.orange.ru.domain.product.ProductItem;
import com.orange.ru.domain.product.enums.ProductItemType;
import com.orange.ru.mongodb.reference.installation.Installation;
import org.joda.money.BigMoney;
import org.springframework.data.repository.Repository;
/**
 * .
 * User: Зайнуллин Радик
 * Date: 12.07.13
 */
public interface InstallationRepository extends Repository<Installation, Long> {
  Installation findOne(Long id);
  Installation findByIndex(String index);
  Installation findByOpportunityTypeAndContractTermAndSpeed(ProductItemType type, Integer contractTerm, Integer speed);
  public BigMoney getMoneyByItem(ProductItem item, int contractTerm);
}
