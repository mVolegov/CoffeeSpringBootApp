package com.mvoleg.coffeespringbootapp.api.restcontroller

import com.mvoleg.coffeespringbootapp.api.dto.menucategory.MenuCategoryDTO
import com.mvoleg.coffeespringbootapp.service.MenuCategoryService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/menu-categories")
@Tag(name = "Категории меню", description = "Эндпоинты для работы с категориями меню")
class MenuCategoryRestController(
    @Autowired
    private val menuCategoryService: MenuCategoryService
) {

    @GetMapping
    @Operation(summary = "Получить все категории меню")
    fun getAll(): List<MenuCategoryDTO> = menuCategoryService.getAll()

    @GetMapping("/{id}")
    @Operation(summary = "Получить категорию меню по ID")
    fun getById(
        @Parameter(description = "Уникальный ID категории меню")
        @PathVariable("id") id: Long
    ): MenuCategoryDTO = menuCategoryService.getById(id)

    @GetMapping("/search")
    @Operation(summary = "Найти категорию меню по ее ID")
    fun searchMenuCategory(
        @Parameter(description = "Уникальный ID категории меню")
        @RequestParam("prefix") prefix: String
    ): List<MenuCategoryDTO> {
        return menuCategoryService.search(prefix)
    }

    @PostMapping
    @Operation(summary = "Создать категорию меню")
    fun create(@RequestBody dto: MenuCategoryDTO): ResponseEntity<MenuCategoryDTO> {
        val createdMenuCategoryEntity = menuCategoryService.create(dto)
        return ResponseEntity(createdMenuCategoryEntity, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить категорию меню по ее ID")
    fun update(
        @Parameter(description = "Уникальный ID категории меню")
        @PathVariable("id") id: Long,

        @RequestBody dto: MenuCategoryDTO
    ): ResponseEntity<MenuCategoryDTO> {
        val updatedMenuCategoryEntity = menuCategoryService.update(id, dto)
        return ResponseEntity(updatedMenuCategoryEntity, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить категорию меню по ее ID")
    fun delete(
        @Parameter(description = "Уникальный ID категории меню")
        @PathVariable("id") id: Long
    ): ResponseEntity<String> {
        menuCategoryService.delete(id)
        return ResponseEntity("Menu category with id $id was successfully deleted", HttpStatus.OK)
    }
}