package com.mvoleg.coffeespringbootapp.api.mapper;

import com.mvoleg.coffeespringbootapp.api.dto.menuelement.MenuElementDTO;
import com.mvoleg.coffeespringbootapp.api.dto.menuelement.MenuElementUpdateDTO;
import com.mvoleg.coffeespringbootapp.persistence.model.MenuElementEntity;

import java.util.Collections;
import java.util.HashSet;

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
                new HashSet<>(MenuCategoryMapper.toDTOCollection(entity.getCategories()))
        );
    }

    public static MenuElementEntity fromMenuElementDTOtoEntity(MenuElementDTO dto) {
        MenuElementEntity entity = new MenuElementEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setKcal(dto.getKcal());
        entity.setProteins(dto.getProteins());
        entity.setFats(dto.getFats());
        entity.setCarbohydrates(dto.getCarbohydrates());
        entity.setSize(dto.getSize());
        entity.setPrice(dto.getPrice());

        entity.setCategories(
                dto.getCategories() == null ?
                Collections.emptySet() : MenuCategoryMapper.toEntityCollection(dto.getCategories())
        );

        return entity;
    }

    public static MenuElementEntity fromMenuElementUpdateDTOtoEntity(MenuElementUpdateDTO dto) {
        MenuElementEntity entity = new MenuElementEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setKcal(dto.getKcal());
        entity.setProteins(dto.getProteins());
        entity.setFats(dto.getFats());
        entity.setCarbohydrates(dto.getCarbohydrates());
        entity.setSize(dto.getSize());
        entity.setPrice(dto.getPrice());

        return entity;
    }
}
