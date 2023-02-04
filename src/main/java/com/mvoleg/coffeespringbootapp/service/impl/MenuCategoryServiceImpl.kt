package com.mvoleg.coffeespringbootapp.service.impl

import com.mvoleg.coffeespringbootapp.dto.menucategory.MenuCategoryDTO
import com.mvoleg.coffeespringbootapp.entity.MenuCategoryEntity
import com.mvoleg.coffeespringbootapp.exception.MenuCategoryAlreadyAssignedException
import com.mvoleg.coffeespringbootapp.exception.MenuCategoryNotFoundException
import com.mvoleg.coffeespringbootapp.mapper.MenuCategoryMapper
import com.mvoleg.coffeespringbootapp.repository.MenuCategoryRepository
import com.mvoleg.coffeespringbootapp.repository.MenuElementRepository
import com.mvoleg.coffeespringbootapp.service.MenuCategoryService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class MenuCategoryServiceImpl(
    private val menuCategoryRepository: MenuCategoryRepository,
    private val menuElementRepository: MenuElementRepository
): MenuCategoryService {

    override fun getAll(): List<MenuCategoryDTO> {
        return menuCategoryRepository.findByOrderByNameDesc().map { MenuCategoryMapper.toDTO(it) }
    }

    override fun getById(id: Long): MenuCategoryDTO {
        val menuCategoryEntity = menuCategoryRepository
            .findByIdOrNull(id)
            ?: throw MenuCategoryNotFoundException(id)

        return MenuCategoryMapper.toDTO(menuCategoryEntity)
    }

    override fun search(prefix: String): List<MenuCategoryDTO> {
        return menuCategoryRepository
            .findByNameStartingWithIgnoreCaseOrderByName(prefix)
            .map { MenuCategoryMapper.toDTO(it) }
    }

    override fun create(dto: MenuCategoryDTO): MenuCategoryDTO {
        val savedMenuCategoryEntity = menuCategoryRepository.save(MenuCategoryMapper.toEntity(dto))

        return MenuCategoryMapper.toDTO(savedMenuCategoryEntity)
    }

    @Transactional
    override fun update(id: Long, dto: MenuCategoryDTO): MenuCategoryDTO {
        val existingMenuCategory = menuCategoryRepository
            .findByIdOrNull(id)
            ?: throw MenuCategoryNotFoundException(id)

        existingMenuCategory.name = dto.name

        val updatedMenuCategoryEntity = menuCategoryRepository.save(existingMenuCategory)

        return MenuCategoryMapper.toDTO(updatedMenuCategoryEntity)
    }

    @Transactional
    override fun delete(id: Long) {
        val menuCategoryEntity = menuCategoryRepository
            .findByIdOrNull(id)
            ?: throw MenuCategoryNotFoundException(id)

        val allMenuCategoryEntities: List<MenuCategoryEntity> = menuElementRepository
            .findAll()
            .flatMap { it.categories }

        if (allMenuCategoryEntities.contains(menuCategoryEntity)) {
            throw MenuCategoryAlreadyAssignedException(id)
        }

        menuCategoryRepository.deleteById(id)
    }
}
