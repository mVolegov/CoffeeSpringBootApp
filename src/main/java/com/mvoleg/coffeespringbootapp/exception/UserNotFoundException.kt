package com.mvoleg.coffeespringbootapp.exception

import org.springframework.http.HttpStatus

class UserNotFoundException(username: String): BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError(
        errorCode = "user.not.found",
        description = "User with username: $username  not found"
    )
)