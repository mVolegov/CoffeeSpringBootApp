package com.mvoleg.coffeespringbootapp.service.impl;

import com.mvoleg.coffeespringbootapp.api.dto.auth.AuthResponseDTO;
import com.mvoleg.coffeespringbootapp.api.dto.auth.LoginDTO;
import com.mvoleg.coffeespringbootapp.api.dto.auth.RegisterDTO;
import com.mvoleg.coffeespringbootapp.persistence.model.RoleEntity;
import com.mvoleg.coffeespringbootapp.persistence.model.Status;
import com.mvoleg.coffeespringbootapp.persistence.model.UserEntity;
import com.mvoleg.coffeespringbootapp.exception.UsernameIsTakenException;
import com.mvoleg.coffeespringbootapp.persistence.dao.RoleRepository;
import com.mvoleg.coffeespringbootapp.persistence.dao.UserRepository;
import com.mvoleg.coffeespringbootapp.security.jwt.JwtTokenProvider;
import com.mvoleg.coffeespringbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    @Override
    public UserEntity register(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new UsernameIsTakenException(registerDTO.getUsername());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userEntity.setStatus(Status.ACTIVE);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        RoleEntity roleEntity = roleRepository.findByName("ROLE_CUSTOMER").get();
        userEntity.setRoles(Collections.singletonList(roleEntity));

        return userRepository.save(userEntity);
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return new AuthResponseDTO(token);
    }
}
