package com.mvoleg.coffeespringbootapp.exception

data class ApiError(
    val errorCode: String,
    val description: String
)
