package com.mvoleg.coffeespringbootapp.exception

import org.springframework.http.HttpStatus

class MenuElementNotFoundException(id: Long): BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError(
        errorCode = "menuelement.not.found",
        description = "Menu element with id $id not found"
    )
)