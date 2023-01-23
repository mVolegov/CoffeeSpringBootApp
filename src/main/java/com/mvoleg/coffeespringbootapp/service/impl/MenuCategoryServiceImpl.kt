package com.mvoleg.coffeespringbootapp.service.impl

import com.mvoleg.coffeespringbootapp.dto.MenuCategoryDTO
import com.mvoleg.coffeespringbootapp.exception.MenuCategoryNotFoundException
import com.mvoleg.coffeespringbootapp.mapper.MenuCategoryMapper
import com.mvoleg.coffeespringbootapp.repository.MenuCategoryRepository
import com.mvoleg.coffeespringbootapp.service.MenuCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MenuCategoryServiceImpl(
    @Autowired
    private val menuCategoryRepository: MenuCategoryRepository
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

    override fun update(id: Long, dto: MenuCategoryDTO): MenuCategoryDTO {
        val existingMenuCategory = menuCategoryRepository
            .findByIdOrNull(id)
            ?: throw MenuCategoryNotFoundException(id)

        existingMenuCategory.name = dto.name

        val updatedMenuCategoryEntity = menuCategoryRepository.save(existingMenuCategory)

        return MenuCategoryMapper.toDTO(updatedMenuCategoryEntity)
    }

    override fun delete(id: Long) {
        val existingMenuCategory = menuCategoryRepository.findByIdOrNull(id)
            ?: throw MenuCategoryNotFoundException(id)

        menuCategoryRepository.deleteById(existingMenuCategory.id)
    }

//    private fun toDTO(menuCategoryEntity: MenuCategoryEntity): MenuCategoryDTO {
//        return MenuCategoryDTO(menuCategoryEntity.name)
//    }

//    private fun toEntity(menuCategoryDTO: MenuCategoryDTO): MenuCategoryEntity {
//        val menuCategoryEntity = MenuCategoryEntity()
//        menuCategoryEntity.name = menuCategoryDTO.name
//        return menuCategoryEntity
//    }
}

