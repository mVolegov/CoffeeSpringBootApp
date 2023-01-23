package com.mvoleg.coffeespringbootapp.service;

import com.mvoleg.coffeespringbootapp.dto.AuthResponseDTO;
import com.mvoleg.coffeespringbootapp.dto.LoginDTO;
import com.mvoleg.coffeespringbootapp.dto.RegisterDTO;
import com.mvoleg.coffeespringbootapp.entity.UserEntity;

public interface UserService {

    UserEntity register(RegisterDTO registerDTO);

    AuthResponseDTO login(LoginDTO loginDTO);
}
