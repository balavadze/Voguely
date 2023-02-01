package com.wit.myapplication.model

data class Cart(
    val key: String,
    val cartResponse: CartResponse
)

class QuantityProduct(
    val quantity: Int,
    val product: Product
)
