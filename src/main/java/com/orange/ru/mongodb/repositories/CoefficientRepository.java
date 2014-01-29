package com.orange.ru.mongodb.repositories;

import com.orange.ru.mongodb.coefficient.Coefficient;
import org.springframework.data.repository.Repository;
import java.util.List;

public interface CoefficientRepository extends Repository<Coefficient, Long> {
  List<Coefficient> findAll();
  Coefficient findBySpeed(Integer speed);
  void save(Coefficient in);
  double findBySpeedAndTown(Integer speed, String town);
}