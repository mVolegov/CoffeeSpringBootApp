package com.mvoleg.coffeespringbootapp.service.impl;

import com.mvoleg.coffeespringbootapp.api.dto.menuelement.MenuElementCategoryDTO;
import com.mvoleg.coffeespringbootapp.api.dto.menuelement.MenuElementDTO;
import com.mvoleg.coffeespringbootapp.api.dto.menuelement.MenuElementUpdateDTO;
import com.mvoleg.coffeespringbootapp.persistence.dao.MenuCategoryRepository;
import com.mvoleg.coffeespringbootapp.persistence.dao.MenuElementRepository;
import com.mvoleg.coffeespringbootapp.persistence.model.MenuCategoryEntity;
import com.mvoleg.coffeespringbootapp.persistence.model.MenuElementEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class MenuElementServiceImplTest {

    @Mock
    private MenuElementRepository menuElementRepository;

    @Mock
    private MenuCategoryRepository menuCategoryRepository;

    @InjectMocks
    private MenuElementServiceImpl underTest;

    private MenuElementEntity menuElementEntity;
    private MenuElementEntity updatedMenuElementEntity;
    private MenuElementDTO menuElementDTO;
    private MenuElementUpdateDTO menuElementUpdateDTO;
    private MenuCategoryEntity menuCategoryEntity;
    private MenuElementCategoryDTO menuElementCategoryDTO;

    @BeforeEach
    void setUp() {
        menuElementEntity = new MenuElementEntity();
        menuElementEntity.setName("Кофе");
        menuElementEntity.setDescription("Какое-то описание");
        menuElementEntity.setKcal(0.2);
        menuElementEntity.setProteins(0.2);
        menuElementEntity.setFats(0.2);
        menuElementEntity.setCarbohydrates(0.2);
        menuElementEntity.setSize(0.2);
        menuElementEntity.setPrice(BigDecimal.TEN);
        menuElementEntity.setCategories(Collections.emptySet());

        updatedMenuElementEntity = new MenuElementEntity();
        updatedMenuElementEntity.setName("Кофе updated");
        updatedMenuElementEntity.setDescription("Какое-то описание updated");
        updatedMenuElementEntity.setKcal(0.4);
        updatedMenuElementEntity.setProteins(0.4);
        updatedMenuElementEntity.setFats(0.4);
        updatedMenuElementEntity.setCarbohydrates(0.4);
        updatedMenuElementEntity.setSize(0.4);
        updatedMenuElementEntity.setPrice(BigDecimal.ONE);

        menuElementDTO = new MenuElementDTO();
        menuElementDTO.setName("Кофе");
        menuElementDTO.setDescription("Какое-то описание");
        menuElementDTO.setKcal(0.2);
        menuElementDTO.setProteins(0.2);
        menuElementDTO.setFats(0.2);
        menuElementDTO.setCarbohydrates(0.2);
        menuElementDTO.setSize(0.2);
        menuElementDTO.setPrice(BigDecimal.TEN);
        menuElementDTO.setCategories(Collections.emptySet());

        menuElementUpdateDTO = new MenuElementUpdateDTO();
        menuElementUpdateDTO.setName("Кофе updated");
        menuElementUpdateDTO.setDescription("Какое-то описание updated");
        menuElementUpdateDTO.setKcal(0.4);
        menuElementUpdateDTO.setProteins(0.4);
        menuElementUpdateDTO.setFats(0.4);
        menuElementUpdateDTO.setCarbohydrates(0.4);
        menuElementUpdateDTO.setSize(0.4);
        menuElementUpdateDTO.setPrice(BigDecimal.ONE);

        menuCategoryEntity = new MenuCategoryEntity();
        menuCategoryEntity.setName("Категория 1");
    }

    @Test
    void getAll() {
        Mockito.when(menuElementRepository.findAll()).thenReturn(Arrays.asList(menuElementEntity));

        List<MenuElementDTO> menuElementDTOs = underTest.getAll();

        Assertions.assertThat(menuElementDTOs).isNotNull();
        Assertions.assertThat(menuElementDTOs.get(0)).isEqualTo(menuElementDTO);
    }

    @Test
    void shouldFindMenuElementByID() {
        Mockito.when(menuElementRepository.findById(1L)).thenReturn(Optional.ofNullable(menuElementEntity));

        MenuElementDTO returnedMenuElementDTO = underTest.getById(1L);

        Assertions.assertThat(returnedMenuElementDTO).isNotNull();
        Assertions.assertThat(returnedMenuElementDTO).isEqualTo(menuElementDTO);
    }

    @Test
    void shouldCreateMenuElement() {
        // when
        Mockito.when(menuElementRepository.save(Mockito.any(MenuElementEntity.class))).thenReturn(menuElementEntity);

        MenuElementDTO savedMenuElementDTO = underTest.create(menuElementDTO);

        // then
        ArgumentCaptor<MenuElementEntity> captor = ArgumentCaptor.forClass(MenuElementEntity.class);

        Mockito.verify(menuElementRepository).save(captor.capture());

        MenuElementEntity capturedMenuElement = captor.getValue();

        Assertions.assertThat(savedMenuElementDTO).isNotNull();
        Assertions.assertThat(capturedMenuElement.getName()).isEqualTo(menuElementDTO.getName());
    }

    @Test
    void shouldUpdateMenuElement() {
        // when
        Mockito.when(menuElementRepository.findById(1L)).thenReturn(Optional.ofNullable(menuElementEntity));
        Mockito
                .when(menuElementRepository.save(Mockito.any(MenuElementEntity.class)))
                .thenReturn(updatedMenuElementEntity);

        MenuElementDTO savedMenuElementDTO = underTest.update(1L, menuElementUpdateDTO);

        // then
        ArgumentCaptor<MenuElementEntity> captor = ArgumentCaptor.forClass(MenuElementEntity.class);

        Mockito.verify(menuElementRepository).save(captor.capture());

        MenuElementEntity capturedMenuElement = captor.getValue();

        Assertions.assertThat(savedMenuElementDTO).isNotNull();
        Assertions.assertThat(savedMenuElementDTO).isInstanceOf(MenuElementDTO.class);
        Assertions.assertThat(capturedMenuElement.getPrice()).isEqualTo(updatedMenuElementEntity.getPrice());
        Assertions.assertThat(capturedMenuElement.getName()).isEqualTo(updatedMenuElementEntity.getName());
    }

    @Test
    void shouldDeleteMenuElement() {
        // when
        Mockito.when(menuElementRepository.findById(1L)).thenReturn(Optional.ofNullable(menuElementEntity));

        org.junit.jupiter.api.Assertions.assertAll(() -> underTest.delete(1L));
    }

    @Test
    void assignCategory() {
        // when
        long menuElementEntityID = 1L;
        long menuCategoryEntityID = 1L;

        Mockito
                .when(menuElementRepository.save(Mockito.any(MenuElementEntity.class)))
                .thenReturn(menuElementEntity);
        Mockito
                .when(menuElementRepository.findById(menuElementEntityID))
                .thenReturn(Optional.ofNullable(menuElementEntity));
        Mockito
                .when(menuCategoryRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(menuCategoryEntity));

        MenuElementDTO result = underTest.assignCategory(
                new MenuElementCategoryDTO(menuElementEntityID, menuCategoryEntityID)
        );

        // then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getCategories()).hasSize(1);
    }

    @Test
    void deleteCategory() {
        // when
        long menuElementEntityID = 1L;
        long menuCategoryEntityID = 1L;

        Mockito
                .when(menuElementRepository.save(Mockito.any(MenuElementEntity.class)))
                .thenReturn(menuElementEntity);
        Mockito
                .when(menuElementRepository.findById(menuElementEntityID))
                .thenReturn(Optional.ofNullable(menuElementEntity));
        Mockito
                .when(menuCategoryRepository.findById(Mockito.any(Long.class)))
                .thenReturn(Optional.ofNullable(menuCategoryEntity));

        MenuElementCategoryDTO dto = new MenuElementCategoryDTO(menuElementEntityID, menuCategoryEntityID);

        // TODO надо задать категорию заранее
//        menuElementEntity.setCategories(new HashSet<>(menuCa));

        // then
        org.junit.jupiter.api.Assertions.assertAll(() -> underTest.deleteCategory(dto));
    }
}