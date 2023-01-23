package com.mvoleg.coffeespringbootapp.repository;

import com.mvoleg.coffeespringbootapp.entity.OrderCompositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCompositionRepository extends JpaRepository<OrderCompositionEntity, Long> {
}
