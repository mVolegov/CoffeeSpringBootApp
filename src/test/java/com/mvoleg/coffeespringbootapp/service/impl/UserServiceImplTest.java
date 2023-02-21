package com.mvoleg.coffeespringbootapp.service.impl;

import com.mvoleg.coffeespringbootapp.api.dto.auth.AuthResponseDTO;
import com.mvoleg.coffeespringbootapp.api.dto.auth.LoginDTO;
import com.mvoleg.coffeespringbootapp.api.dto.auth.RegisterDTO;
import com.mvoleg.coffeespringbootapp.exception.UsernameIsTakenException;
import com.mvoleg.coffeespringbootapp.persistence.dao.RoleRepository;
import com.mvoleg.coffeespringbootapp.persistence.dao.UserRepository;
import com.mvoleg.coffeespringbootapp.persistence.model.RoleEntity;
import com.mvoleg.coffeespringbootapp.persistence.model.UserEntity;
import com.mvoleg.coffeespringbootapp.security.jwt.JwtTokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private UserServiceImpl underTest;

    @Test
    void shouldRegisterUser() {
        // given
        RegisterDTO registerDTO = new RegisterDTO("alex", "1234");

        RoleEntity roleCustomer = new RoleEntity();
        roleCustomer.setName("ROLE_CUSTOMER");

        // when
        Mockito.when(roleRepository.findByName(Mockito.anyString())).thenReturn(Optional.of(roleCustomer));

        underTest.register(registerDTO);

        // then
        ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);

        Mockito.verify(userRepository).save(userArgumentCaptor.capture());

        UserEntity capturedUser = userArgumentCaptor.getValue();
        Assertions.assertThat(capturedUser.getUsername()).isEqualTo(registerDTO.getUsername());
        Assertions.assertThat(capturedUser.getRoles().get(0).getName()).isEqualTo("ROLE_CUSTOMER");
        Assertions.assertThat(capturedUser.getPassword()).isEqualTo(passwordEncoder.encode("1234"));
    }

    @Test
    void shouldThrowUserNameIsTakenExceptionWhenRegisteringNewUser() {
        // given
        RegisterDTO registerDTO = new RegisterDTO("alex", "1234");

        RoleEntity roleCustomer = new RoleEntity();
        roleCustomer.setName("ROLE_CUSTOMER");

        // when
        // then
        Mockito.when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(true);

        Assertions
                .assertThatThrownBy(() -> underTest.register(registerDTO))
                .isInstanceOf(UsernameIsTakenException.class)
                .hasMessageContaining("Username " + registerDTO.getUsername() + " is taken");

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }
}