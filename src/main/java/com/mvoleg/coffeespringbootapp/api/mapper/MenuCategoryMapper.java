package com.mvoleg.coffeespringbootapp.api.mapper;

import com.mvoleg.coffeespringbootapp.api.dto.menucategory.MenuCategoryDTO;
import com.mvoleg.coffeespringbootapp.model.entity.MenuCategoryEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class MenuCategoryMapper {

    public static MenuCategoryDTO toDTO(MenuCategoryEntity entity) {
        return new MenuCategoryDTO(entity.getId(), entity.getName());
    }

    public static Set<MenuCategoryDTO> toDTOCollection(Set<MenuCategoryEntity> entityCollection) {
        Set<MenuCategoryDTO> menuCategoryDTOs = entityCollection
                .stream()
                .map(MenuCategoryMapper::toDTO)
                .collect(Collectors.toSet());

        return menuCategoryDTOs;
    }

    public static MenuCategoryEntity toEntity(MenuCategoryDTO dto) {
        MenuCategoryEntity menuCategoryEntity = new MenuCategoryEntity();
        menuCategoryEntity.setId(dto.getId());
        menuCategoryEntity.setName(dto.getName());

        return menuCategoryEntity;
    }

    public static Set<MenuCategoryEntity> toEntityCollection(Set<MenuCategoryDTO> dtoCollection) {
        Set<MenuCategoryEntity> menuCategoryEntities = dtoCollection
                .stream()
                .map(MenuCategoryMapper::toEntity)
                .collect(Collectors.toSet());

        return menuCategoryEntities;
    }
}
