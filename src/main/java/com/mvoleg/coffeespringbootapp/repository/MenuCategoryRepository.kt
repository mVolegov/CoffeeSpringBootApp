package com.mvoleg.coffeespringbootapp.repository

import com.mvoleg.coffeespringbootapp.entity.MenuCategoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuCategoryRepository: JpaRepository<MenuCategoryEntity, Long> {

    fun findByOrderByNameDesc(): List<MenuCategoryEntity>

    fun findByNameStartingWithIgnoreCaseOrderByName(prefix: String): List<MenuCategoryEntity>
}