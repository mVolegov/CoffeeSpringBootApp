package com.mvoleg.coffeespringbootapp.repository;

import com.mvoleg.coffeespringbootapp.model.entity.Status;
import com.mvoleg.coffeespringbootapp.model.entity.UserEntity;
import com.mvoleg.coffeespringbootapp.model.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
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
    void itShouldReturnFalseIfUserNotExists() {
        // when
        Boolean expected = underTest.existsByUsername(notExistingUsername);

        // then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldReturnTrueIfUserExists() {
        // when
        boolean expected = underTest.existsByUsername(username);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldNotFindUserByNotExistingUsername() {
        // when
        Optional<UserEntity> actual = underTest.findByUsername(notExistingUsername);

        // then
        assertThat(actual.isPresent()).isFalse();
    }

    @Test
    void itShouldFindUserByExistingUsername() {
        // when
        Optional<UserEntity> actual = underTest.findByUsername(username);

        // then
        assertThat(actual.get().getUsername()).isEqualTo(username);
    }
}