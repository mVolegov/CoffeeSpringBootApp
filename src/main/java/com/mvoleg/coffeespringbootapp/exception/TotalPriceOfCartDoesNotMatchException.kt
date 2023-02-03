package com.mvoleg.coffeespringbootapp.exception

import org.springframework.http.HttpStatus

class TotalPriceOfCartDoesNotMatchException(expected: Double, got: Double): BaseException(
    HttpStatus.BAD_REQUEST,
    ApiError(
        errorCode = "total.cart.price.does.not.match",
        description = "Total cost of the goods in the cart does not match the declared one: " +
                "expected=$expected, but got = $got"
    )
)