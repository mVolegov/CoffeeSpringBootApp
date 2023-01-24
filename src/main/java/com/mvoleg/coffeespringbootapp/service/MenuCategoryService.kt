package com.mvoleg.coffeespringbootapp.service

import com.mvoleg.coffeespringbootapp.dto.menucategory.MenuCategoryDTO

interface MenuCategoryService {

    fun getAll(): List<MenuCategoryDTO>

    fun getById(id: Long): MenuCategoryDTO

    fun search(prefix: String): List<MenuCategoryDTO>

    fun create(dto: MenuCategoryDTO): MenuCategoryDTO

    fun update(id: Long, dto: MenuCategoryDTO): MenuCategoryDTO

    fun delete(id: Long)
}