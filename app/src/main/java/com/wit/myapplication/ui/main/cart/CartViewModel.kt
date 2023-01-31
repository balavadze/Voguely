package com.wit.myapplication.ui.main.cart

import DeleteFromCartDataSource
import QuantityUpdate
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.myapplication.model.Other
import com.wit.myapplication.remote.GetCartDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch



class CartViewModel : ViewModel() {
    private val _cartProducts = MutableStateFlow<List<Other>>(emptyList())
    val cartProducts = _cartProducts.asStateFlow()

    private val _noItemsInCart = MutableStateFlow(false)
    val noItemsInCart = _noItemsInCart.asStateFlow()

    private val getCartDataSource = GetCartDataSource()

    private var deleteFromCartDataSource = DeleteFromCartDataSource()

    private var quantityUpdate = QuantityUpdate()


    fun loadCartItems() {
        viewModelScope.launch {
            val cart = getCartDataSource.getItemsInCart()
            _cartProducts.update { cart }
            _noItemsInCart.update { cart.isEmpty() }
        }
    }

    fun getTotalPrice(other: List<Other>): Int {
        val getTotalPrice = other.sumBy { it.quantity * it.product.price }
        Log.d("CartViewModel", "Total price: $getTotalPrice")
        return getTotalPrice
    }

    fun deleteFromCart() {
        viewModelScope.launch(Dispatchers.IO) {
            cartProducts.value.let {
                deleteFromCartDataSource.deleteProductFromCart(cartProducts.value.toString())
            }
        }
    }

}
