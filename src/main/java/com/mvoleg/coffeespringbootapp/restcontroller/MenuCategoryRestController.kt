package com.mvoleg.coffeespringbootapp.restcontroller

import com.mvoleg.coffeespringbootapp.dto.menucategory.MenuCategoryDTO
import com.mvoleg.coffeespringbootapp.service.MenuCategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu-categories")
class MenuCategoryRestController(
    @Autowired
    private val menuCategoryService: MenuCategoryService
) {

    @GetMapping
    fun getAll(): List<MenuCategoryDTO> = menuCategoryService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): MenuCategoryDTO = menuCategoryService.getById(id)

    @GetMapping("/search")
    fun searchMenuCategory(@RequestParam("prefix") prefix: String): List<MenuCategoryDTO> {
        return menuCategoryService.search(prefix)
    }

    @PostMapping
    fun create(@RequestBody dto: MenuCategoryDTO): ResponseEntity<MenuCategoryDTO> {
        val createdMenuCategoryEntity = menuCategoryService.create(dto)
        return ResponseEntity(createdMenuCategoryEntity, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody dto: MenuCategoryDTO): ResponseEntity<MenuCategoryDTO> {
        val updatedMenuCategoryEntity = menuCategoryService.update(id, dto)
        return ResponseEntity(updatedMenuCategoryEntity, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<String> {
        menuCategoryService.delete(id)
        return ResponseEntity("Menu category with id $id was successfully deleted", HttpStatus.OK)
    }
}