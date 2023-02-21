package com.mvoleg.coffeespringbootapp.repository;

import com.mvoleg.coffeespringbootapp.persistence.model.MenuCategoryEntity;
import com.mvoleg.coffeespringbootapp.persistence.dao.MenuCategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MenuCategoryRepositoryTest {

    @Autowired
    private MenuCategoryRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldNotReturnAnything() {
        // given
        underTest.saveAll(Collections.emptyList());  // We don't save anything here

        // when
        List<MenuCategoryEntity> all = underTest.findAll();

        // then
        assertThat(all.isEmpty()).isTrue();
    }

    @Test
    void shouldReturnMenuCategoriesOrderedByNameDesc() {
        // given
        String nameFirst = "Горячее";
        String nameSecond = "Холодное";

        MenuCategoryEntity menuCategoryEntity1 = new MenuCategoryEntity();
        menuCategoryEntity1.setName(nameFirst);

        MenuCategoryEntity menuCategoryEntity2 = new MenuCategoryEntity();
        menuCategoryEntity2.setName(nameSecond);

        underTest.saveAll(Arrays.asList(menuCategoryEntity1, menuCategoryEntity2));

        // when
        List<MenuCategoryEntity> actualEntities = underTest.findByOrderByNameDesc();

        // then
        String expectedFistName = "Холодное";
        String expectedSecondName = "Горячее";

        String actualFistName = actualEntities.get(0).getName();
        String actualSecondName = actualEntities.get(1).getName();

        assertThat(actualFistName).isEqualTo(expectedFistName);
        assertThat(actualSecondName).isEqualTo(expectedSecondName);

        assertThat(actualFistName).isNotEqualTo(expectedSecondName);
        assertThat(actualSecondName).isNotEqualTo(expectedFistName);
    }
}
