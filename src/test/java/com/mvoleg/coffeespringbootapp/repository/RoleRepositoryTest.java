package com.mvoleg.coffeespringbootapp.repository;

import com.mvoleg.coffeespringbootapp.persistence.dao.RoleRepository;
import com.mvoleg.coffeespringbootapp.persistence.model.RoleEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository underTest;

    private final String notExistingRole = "Some non existing role";

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindRoleByName() {
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
    void shouldNotFoundRoleByNotExistingName() {
        // when
        Optional<RoleEntity> actual = underTest.findByName(notExistingRole);

        // then
        assertThat(actual.isPresent()).isFalse();
    }
}