package com.mvoleg.coffeespringbootapp.persistence.dao;

import com.mvoleg.coffeespringbootapp.persistence.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);
}
