package com.mvoleg.coffeespringbootapp.persistence.repository;

import com.mvoleg.coffeespringbootapp.persistence.model.OrderCompositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCompositionRepository extends JpaRepository<OrderCompositionEntity, Long> {
}
