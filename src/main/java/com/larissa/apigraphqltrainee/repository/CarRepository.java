package com.larissa.apigraphqltrainee.repository;

import com.larissa.apigraphqltrainee.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
