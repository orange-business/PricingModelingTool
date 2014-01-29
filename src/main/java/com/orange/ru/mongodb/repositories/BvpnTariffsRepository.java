package com.orange.ru.mongodb.repositories;

import com.orange.ru.domain.product.BusinessVpn;
import com.orange.ru.mongodb.reference.bvpntariffs.BusinessVpnTariffs;
import org.joda.money.BigMoney;
import org.springframework.data.repository.Repository;

public interface BvpnTariffsRepository extends Repository<BusinessVpnTariffs, Long> {
  BusinessVpnTariffs findByTown(String town);
  BigMoney getFixValueByProduct(BusinessVpn product);
}