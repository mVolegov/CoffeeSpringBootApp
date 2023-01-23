package com.mvoleg.coffeespringbootapp.service;

import com.mvoleg.coffeespringbootapp.dto.MenuElementCategoryDTO;
import com.mvoleg.coffeespringbootapp.dto.MenuElementDTO;
import com.mvoleg.coffeespringbootapp.entity.MenuElementEntity;

import java.util.List;

public interface MenuElementService {

    List<MenuElementDTO> getAll();

    MenuElementDTO getById(Long id);

    MenuElementDTO create(MenuElementDTO dto);

    MenuElementDTO assignCategory(MenuElementCategoryDTO dto);

    MenuElementDTO update(Long id, MenuElementDTO dto);

    void delete(Long id);
}
