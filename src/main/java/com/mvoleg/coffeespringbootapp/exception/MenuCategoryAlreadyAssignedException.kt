package com.mvoleg.coffeespringbootapp.exception

import org.springframework.http.HttpStatus

class MenuCategoryAlreadyAssignedException(menuCategoryId: Long): BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError(
        errorCode = "menu.category.already.assigned",
        description = "Menu category with id $menuCategoryId is already assigned to menu element"
    )
)