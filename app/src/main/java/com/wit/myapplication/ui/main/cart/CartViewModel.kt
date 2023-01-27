package com.wit.myapplication.ui.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class CartViewModel : ViewModel() {
    val cartProducts = MutableStateFlow<List<Cart>>(emptyList())

    init {
        loadCart()
    }

    private fun loadCart() {
        viewModelScope.launch() {
            val mockCartItems: List<Cart>


            /*listOf(
            CartItems(
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
                "Headphone",
                "Rp. 9.99",
                "x1",
                "remove",

                ),
            CartItems(
                "https://images.unsplash.com/photo-1607522370275-f14206abe5d3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1721&q=80",
                "Shoe",
                "Rp. 9.99",
                "x1",
                "remove",

                ),
            CartItems(
                "https://plus.unsplash.com/premium_photo-1671028365039-d9294e26e482?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8c2hvZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60",
                "Shoe",
                "Rp. 9.99",
                "x1",
                "remove",

                ),
            CartItems(
                "https://images.unsplash.com/photo-1633966887768-64f9a867bdba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHRzaGlydHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60",
                "Tshirt",
                "Rp. 9.99",
                "1",
                "remove",

                ),
            CartItems(
                "https://images.unsplash.com/photo-1580051235428-f88ae8a2d53b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHVtYnJlbGxhfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
                "Umbrella",
                "Rp. 9.99",
                "x1",
                "remove",
            ),
        )
        cartProducts.update { mockCartItems }*/
        }
    }

    fun fetchCart(): MutableList<Cart> {
        val cartItems: MutableList<Cart> = mutableListOf<Cart>()
        viewModelScope.launch {

        }
        return cartItems
    }
}
/*var cart = _cartProducts
val cartItems: StateFlow<List<CartItems>> = _cartProducts*/

/* fun updateCart() {
     _cartProducts.value = Cart.getCart()
 }

 fun getAllProducts() = _cartProducts.value
}*/

