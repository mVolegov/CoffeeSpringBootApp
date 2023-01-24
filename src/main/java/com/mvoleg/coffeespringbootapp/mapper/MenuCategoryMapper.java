package com.mvoleg.coffeespringbootapp.mapper;

import com.mvoleg.coffeespringbootapp.dto.menucategory.MenuCategoryDTO;
import com.mvoleg.coffeespringbootapp.entity.MenuCategoryEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MenuCategoryMapper {

    public static MenuCategoryDTO toDTO(MenuCategoryEntity entity) {
        return new MenuCategoryDTO(entity.getId(), entity.getName());
    }

    public static List<MenuCategoryDTO> toDTOCollection(List<MenuCategoryEntity> entityCollection) {
        List<MenuCategoryDTO> menuCategoryDTOs = entityCollection
                .stream()
                .map(MenuCategoryMapper::toDTO)
                .collect(Collectors.toList());

        return menuCategoryDTOs;
    }

    public static MenuCategoryEntity toEntity(MenuCategoryDTO dto) {
        MenuCategoryEntity menuCategoryEntity = new MenuCategoryEntity();
        menuCategoryEntity.setId(dto.getId());
        menuCategoryEntity.setName(dto.getName());

        return menuCategoryEntity;
    }

    public static List<MenuCategoryEntity> toEntityCollection(List<MenuCategoryDTO> dtoCollection) {
        List<MenuCategoryEntity> menuCategoryEntities = dtoCollection
                .stream()
                .map(MenuCategoryMapper::toEntity)
                .collect(Collectors.toList());

//        List<MenuCategoryDTO> menuCategoryDTOs = dtoCollection.orElse(new ArrayList<>());
//
//        List<MenuCategoryEntity> menuCategoryEntities = menuCategoryDTOs
//                .stream()
//                .map(MenuCategoryMapper::toEntity)
//                .collect(Collectors.toList());

        return menuCategoryEntities;
    }
}
