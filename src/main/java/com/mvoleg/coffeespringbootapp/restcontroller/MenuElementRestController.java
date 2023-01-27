package com.mvoleg.coffeespringbootapp.restcontroller;

import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementCategoryDTO;
import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementDTO;
import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementUpdateDTO;
import com.mvoleg.coffeespringbootapp.service.MenuElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-elements")
public class MenuElementRestController {

    private final MenuElementService menuElementService;

    @Autowired
    public MenuElementRestController(MenuElementService menuElementService) {
        this.menuElementService = menuElementService;
    }

    @GetMapping
    public ResponseEntity<List<MenuElementDTO>> getAll() {
        return new ResponseEntity<>(menuElementService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MenuElementDTO> create(@RequestBody MenuElementDTO dto) {
        MenuElementDTO createdMenuElementDTO = menuElementService.create(dto);
        return new ResponseEntity<>(createdMenuElementDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuElementDTO> update(@PathVariable("id") Long id, @RequestBody MenuElementUpdateDTO dto) {
        MenuElementDTO updatedMenuElementDTO = menuElementService.update(id, dto);
        return new ResponseEntity<>(updatedMenuElementDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        menuElementService.delete(id);
        return new ResponseEntity<>("Menu element with id " + id + " was deleted", HttpStatus.OK);
    }

    @PutMapping("/assign-category")
    public ResponseEntity<MenuElementDTO> assignCategory(@RequestBody MenuElementCategoryDTO dto) {
        MenuElementDTO menuElementDTO = menuElementService.assignCategory(dto);
        return new ResponseEntity<>(menuElementDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete-category")
    public ResponseEntity<MenuElementDTO> deleteCategory(@RequestBody MenuElementCategoryDTO dto) {
        MenuElementDTO menuElementDTO = menuElementService.deleteCategory(dto);
        return new ResponseEntity<>(menuElementDTO, HttpStatus.OK);
    }
}
