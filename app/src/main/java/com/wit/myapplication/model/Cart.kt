package com.wit.myapplication.ui.main.cart


data class CartItems(
    val cartPhoto: String,
    val cartDescription: String,
    val cartPrice: String,
    val amount: String,
    val remove: String,
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
