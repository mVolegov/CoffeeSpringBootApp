package com.mvoleg.coffeespringbootapp.repository;

import com.mvoleg.coffeespringbootapp.persistence.model.Status;
import com.mvoleg.coffeespringbootapp.persistence.model.UserEntity;
import com.mvoleg.coffeespringbootapp.persistence.dao.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    private final String username = "Alex";
    private final String notExistingUsername = "Some non existing username";
    private final String password = "alex";

    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setStatus(Status.ACTIVE);

        LocalDateTime now = LocalDateTime.now();
        userEntity.setCreatedAt(now);
        userEntity.setUpdatedAt(now);

        underTest.save(userEntity);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldReturnFalseIfUserNotExists() {
        // when
        Boolean expected = underTest.existsByUsername(notExistingUsername);

        // then
        assertThat(expected).isFalse();
    }

    @Test
    void shouldReturnTrueIfUserExists() {
        // when
        boolean expected = underTest.existsByUsername(username);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void shouldNotFindUserByNotExistingUsername() {
        // when
        Optional<UserEntity> actual = underTest.findByUsername(notExistingUsername);

        // then
        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    void shouldFindUserByExistingUsername() {
        // when
        Optional<UserEntity> actual = underTest.findByUsername(username);

        // then
        assertThat(actual.get().getUsername()).isEqualTo(username);
    }
}