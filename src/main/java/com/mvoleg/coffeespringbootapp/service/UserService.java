package com.mvoleg.coffeespringbootapp.service;

import com.mvoleg.coffeespringbootapp.dto.auth.AuthResponseDTO;
import com.mvoleg.coffeespringbootapp.dto.auth.LoginDTO;
import com.mvoleg.coffeespringbootapp.dto.auth.RegisterDTO;
import com.mvoleg.coffeespringbootapp.entity.UserEntity;

public interface UserService {

    UserEntity register(RegisterDTO registerDTO);

    AuthResponseDTO login(LoginDTO loginDTO);
}
