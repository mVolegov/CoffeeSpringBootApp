package com.mvoleg.coffeespringbootapp.service.impl;

import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementCategoryDTO;
import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementDTO;
import com.mvoleg.coffeespringbootapp.entity.MenuCategoryEntity;
import com.mvoleg.coffeespringbootapp.entity.MenuElementEntity;
import com.mvoleg.coffeespringbootapp.exception.MenuCategoryAlreadyAssignedToMenuElementException;
import com.mvoleg.coffeespringbootapp.exception.MenuCategoryNotFoundException;
import com.mvoleg.coffeespringbootapp.exception.MenuElementNotFoundException;
import com.mvoleg.coffeespringbootapp.mapper.MenuElementMapper;
import com.mvoleg.coffeespringbootapp.repository.MenuCategoryRepository;
import com.mvoleg.coffeespringbootapp.repository.MenuElementRepository;
import com.mvoleg.coffeespringbootapp.service.MenuElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuElementServiceImpl implements MenuElementService {

    private final MenuElementRepository menuElementRepository;
    private final MenuCategoryRepository menuCategoryRepository;

    @Autowired
    public MenuElementServiceImpl(MenuElementRepository menuElementRepository,
                                  MenuCategoryRepository menuCategoryRepository) {
        this.menuElementRepository = menuElementRepository;
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public List<MenuElementDTO> getAll() {
        List<MenuElementEntity> allMenuElementEntities = menuElementRepository.findAll();

        return allMenuElementEntities.stream().map(MenuElementMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public MenuElementDTO getById(Long id) {
        MenuElementEntity menuElementEntity = menuElementRepository
                .findById(id)
                .orElseThrow(() -> new MenuElementNotFoundException(id));

        return MenuElementMapper.toDTO(menuElementEntity);
    }

    @Override
    public MenuElementDTO create(MenuElementDTO dto) {
        MenuElementEntity menuElementEntity = menuElementRepository.save(MenuElementMapper.toEntity(dto));

        return MenuElementMapper.toDTO(menuElementEntity);
    }

    @Override
    public MenuElementDTO assignCategory(MenuElementCategoryDTO dto) {
        MenuElementEntity menuElementEntity = menuElementRepository
                .findById(dto.getMenuElementId())
                .orElseThrow(() -> new MenuElementNotFoundException(dto.getMenuElementId()));

        MenuCategoryEntity menuCategoryEntity = menuCategoryRepository
                .findById(dto.getMenuCategoryId())
                .orElseThrow(() -> new MenuCategoryNotFoundException(dto.getMenuCategoryId()));

        List<MenuCategoryEntity> categories = menuElementEntity.getCategories();

        if (categories.contains(menuCategoryEntity)) {
            throw new MenuCategoryAlreadyAssignedToMenuElementException(dto.getMenuCategoryId());
        }

        categories.add(menuCategoryEntity);
        menuElementEntity.setCategories(categories);

        MenuElementEntity savedMenuElementEntity = menuElementRepository.save(menuElementEntity);

        return MenuElementMapper.toDTO(savedMenuElementEntity);
    }

    @Override
    public MenuElementDTO update(Long id, MenuElementDTO dto) {
        MenuElementEntity menuElementEntityToUpdate = menuElementRepository
                .findById(id)
                .orElseThrow(() -> new MenuElementNotFoundException(id));

        MenuElementEntity menuElementEntity = MenuElementMapper.toEntity(dto);
        menuElementEntity.setId(id);

        MenuElementEntity updatedMenuElementEntity = menuElementRepository.save(menuElementEntity);

        return MenuElementMapper.toDTO(updatedMenuElementEntity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        MenuElementEntity menuElementEntityToDelete = menuElementRepository
                .findById(id)
                .orElseThrow(() -> new MenuElementNotFoundException(id));

        menuElementRepository.delete(menuElementEntityToDelete);
    }
}
