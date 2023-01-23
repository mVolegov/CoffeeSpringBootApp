package com.mvoleg.coffeespringbootapp.exception

import org.springframework.http.HttpStatus

class MenuCategoryNotFoundException(menuCategoryId: Long): BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = "menu.category.not.found",
        description = "Menu category not found with id $menuCategoryId"
    )
)