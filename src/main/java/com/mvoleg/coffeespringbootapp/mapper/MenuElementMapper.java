package com.mvoleg.coffeespringbootapp.mapper;

import com.mvoleg.coffeespringbootapp.dto.MenuCategoryDTO;
import com.mvoleg.coffeespringbootapp.dto.MenuElementDTO;
import com.mvoleg.coffeespringbootapp.entity.MenuElementEntity;
import com.mvoleg.coffeespringbootapp.exception.MenuCategoryNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuElementMapper {

    public static MenuElementDTO toDTO(MenuElementEntity entity) {
        return new MenuElementDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getKcal(),
                entity.getProteins(),
                entity.getFats(),
                entity.getCarbohydrates(),
                entity.getSize(),
                entity.getPrice(),
                new ArrayList<>(MenuCategoryMapper.toDTOCollection(entity.getCategories()))
        );
    }

    public static MenuElementEntity toEntity(MenuElementDTO dto) {
        MenuElementEntity entity = new MenuElementEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setKcal(dto.getKcal());
        entity.setProteins(dto.getProteins());
        entity.setFats(dto.getFats());
        entity.setCarbohydrates(dto.getCarbohydrates());
        entity.setSize(dto.getSize());
        entity.setPrice(dto.getPrice());

//        List<MenuCategoryDTO> menuCategoryDTOs = Optional.ofNullable(dto.getCategories()).orElseGet(ArrayList::new);
//        entity.setCategories(MenuCategoryMapper.toEntityCollection(menuCategoryDTOs));

        entity.setCategories(
                dto.getCategories() == null ?
                Collections.emptyList() : MenuCategoryMapper.toEntityCollection(dto.getCategories())
        );

        return entity;
    }
}
