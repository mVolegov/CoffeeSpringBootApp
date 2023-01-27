package com.mvoleg.coffeespringbootapp.exception

import org.springframework.http.HttpStatus

class MenuCategoryNotAssignedException(menuCategoryId: Long): BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError(
        errorCode = "menu.category.not.assigned",
        description = "Menu category with id $menuCategoryId not assigned to menu element"
    )
)