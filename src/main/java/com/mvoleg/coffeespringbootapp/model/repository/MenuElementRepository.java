package com.mvoleg.coffeespringbootapp.model.repository;

import com.mvoleg.coffeespringbootapp.model.entity.MenuElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuElementRepository extends JpaRepository<MenuElementEntity, Long> {
}
