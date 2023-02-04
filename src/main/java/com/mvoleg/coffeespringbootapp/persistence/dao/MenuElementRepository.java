package com.mvoleg.coffeespringbootapp.persistence.dao;

import com.mvoleg.coffeespringbootapp.persistence.model.MenuElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuElementRepository extends JpaRepository<MenuElementEntity, Long> {
}
