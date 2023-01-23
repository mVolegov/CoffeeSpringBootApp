package com.mvoleg.coffeespringbootapp.exception

import org.springframework.http.HttpStatus

class UsernameIsTakenException(username: String): BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError(
        errorCode = "username.is.taken",
        description = "Username $username is taken"
    )
)