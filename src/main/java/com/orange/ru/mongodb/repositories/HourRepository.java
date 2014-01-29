package com.orange.ru.mongodb.repositories;

import com.orange.ru.core.exception.ProductConfigurationException;
import com.orange.ru.domain.product.ProductItem;
import com.orange.ru.mongodb.reference.Hour;
import org.joda.money.BigMoney;
import org.springframework.data.repository.Repository;

/**
 * .
 * User: radik
 * Date: 12.07.13
 */
public interface HourRepository extends Repository<Hour, Long> {
  Hour findByCost(String cost);
  BigMoney getMoneyByCost(String cost);
  BigMoney calculateByCostAndProductItemType(String cost, ProductItem item) throws ProductConfigurationException;
}
