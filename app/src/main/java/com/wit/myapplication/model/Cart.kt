package com.wit.myapplication.ui.main.cart

import com.wit.myapplication.model.CartResponse


data class Cart(
    val key: String,
    val cartResponse: CartResponse

)
/*

object Cart {
    private val cart = mutableListOf<Product>()

    fun addProduct(product: Product) {
        cart.add(product)
    }

    fun removeProduct(product: Product) {
        cart.remove(product)
    }

    fun getCart(): List<Product> {
        return cart
    }
}*/
