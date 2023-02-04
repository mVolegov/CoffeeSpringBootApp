package com.mvoleg.coffeespringbootapp.persistence.repository;

import com.mvoleg.coffeespringbootapp.persistence.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
