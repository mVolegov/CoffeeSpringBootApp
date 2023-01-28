package com.mvoleg.coffeespringbootapp.repository;

import com.mvoleg.coffeespringbootapp.entity.RoleEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void ItShouldFindRoleByName() {
        // given
        String roleCustomer = "ROLE_CUSTOMER";

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(roleCustomer);

        underTest.save(roleEntity);

        // when
        RoleEntity actual = underTest.findByName(roleCustomer).get();

        // then
        RoleEntity expected = new RoleEntity();
        expected.setName(roleCustomer);

        assertThat(actual.getName()).isEqualTo(roleCustomer);
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }

    @Test
    void itShouldNotFoundRoleByNotExistingName() {
        // TODO
    }
}