package com.mvoleg.coffeespringbootapp.service;

import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementCategoryDTO;
import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementDTO;
import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementUpdateDTO;

import java.util.List;

public interface MenuElementService {

    List<MenuElementDTO> getAll();

    MenuElementDTO getById(Long id);

    MenuElementDTO create(MenuElementDTO dto);

    MenuElementDTO update(Long id, MenuElementUpdateDTO dto);

    void delete(Long id);

    MenuElementDTO assignCategory(MenuElementCategoryDTO dto);

    MenuElementDTO deleteCategory(MenuElementCategoryDTO dto);
}
