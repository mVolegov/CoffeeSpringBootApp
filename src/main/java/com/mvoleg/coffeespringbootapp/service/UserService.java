package com.mvoleg.coffeespringbootapp.service;

import com.mvoleg.coffeespringbootapp.api.dto.auth.AuthResponseDTO;
import com.mvoleg.coffeespringbootapp.api.dto.auth.LoginDTO;
import com.mvoleg.coffeespringbootapp.api.dto.auth.RegisterDTO;
import com.mvoleg.coffeespringbootapp.persistence.model.UserEntity;

public interface UserService {

    UserEntity register(RegisterDTO registerDTO);

    AuthResponseDTO login(LoginDTO loginDTO);
}
