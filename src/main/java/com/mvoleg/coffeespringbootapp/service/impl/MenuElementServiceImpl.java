package com.mvoleg.coffeespringbootapp.service.impl;

import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementCategoryDTO;
import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementDTO;
import com.mvoleg.coffeespringbootapp.dto.menuelement.MenuElementUpdateDTO;
import com.mvoleg.coffeespringbootapp.entity.MenuCategoryEntity;
import com.mvoleg.coffeespringbootapp.entity.MenuElementEntity;
import com.mvoleg.coffeespringbootapp.exception.MenuCategoryAlreadyAssignedException;
import com.mvoleg.coffeespringbootapp.exception.MenuCategoryNotAssignedException;
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
import java.util.Set;
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
        MenuElementEntity menuElementEntity = menuElementRepository.save(MenuElementMapper.fromMenuElementDTOtoEntity(dto));

        return MenuElementMapper.toDTO(menuElementEntity);
    }

    @Override
    public MenuElementDTO update(Long id, MenuElementUpdateDTO dto) {
        MenuElementEntity menuElementEntityToUpdate = menuElementRepository
                .findById(id)
                .orElseThrow(() -> new MenuElementNotFoundException(id));

        MenuElementEntity menuElementEntity = MenuElementMapper.fromMenuElementUpdateDTOtoEntity(dto);
        menuElementEntity.setId(id);
        menuElementEntity.setCategories(menuElementEntityToUpdate.getCategories());

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

    @Override
    public MenuElementDTO assignCategory(MenuElementCategoryDTO dto) {
        Long menuElementId = dto.getMenuElementId();
        MenuElementEntity menuElementEntity = menuElementRepository
                .findById(menuElementId)
                .orElseThrow(() -> new MenuElementNotFoundException(menuElementId));

        Long menuCategoryId = dto.getMenuCategoryId();
        MenuCategoryEntity menuCategoryEntity = menuCategoryRepository
                .findById(menuCategoryId)
                .orElseThrow(() -> new MenuCategoryNotFoundException(menuCategoryId));

        Set<MenuCategoryEntity> categories = menuElementEntity.getCategories();

        if (categories.contains(menuCategoryEntity)) {
            throw new MenuCategoryAlreadyAssignedException(menuCategoryId);
        }

        categories.add(menuCategoryEntity);
        menuElementEntity.setCategories(categories);

        MenuElementEntity savedMenuElementEntity = menuElementRepository.save(menuElementEntity);

        return MenuElementMapper.toDTO(savedMenuElementEntity);
    }

    @Transactional
    @Override
    public MenuElementDTO deleteCategory(MenuElementCategoryDTO dto) {
        Long menuElementId = dto.getMenuElementId();
        MenuElementEntity menuElementEntity = menuElementRepository
                .findById(menuElementId)
                .orElseThrow(() -> new MenuElementNotFoundException(menuElementId));

        Long menuCategoryId = dto.getMenuCategoryId();
        MenuCategoryEntity menuCategoryEntity = menuCategoryRepository
                .findById(menuCategoryId)
                .orElseThrow(() -> new MenuCategoryNotFoundException(menuCategoryId));

        Set<MenuCategoryEntity> categories = menuElementEntity.getCategories();

        if (!categories.contains(menuCategoryEntity)) {
            throw new MenuCategoryNotAssignedException(menuCategoryId);
        }

        categories.remove(menuCategoryEntity);

        MenuElementEntity savedMenuElementEntity = menuElementRepository.save(menuElementEntity);

        return MenuElementMapper.toDTO(savedMenuElementEntity);
    }
}
