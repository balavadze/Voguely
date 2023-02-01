package com.wit.myapplication.ui.main.cart

import DeleteFromCartDataSource
import QuantityUpdateDataSource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.myapplication.model.QuantityProduct
import com.wit.myapplication.remote.GetCartDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CartViewModel : ViewModel() {
    private val _cartProducts = MutableStateFlow<List<QuantityProduct>>(emptyList())
    val cartProducts = _cartProducts.asStateFlow()

    private val _noItemsInCart = MutableStateFlow(false)
    val noItemsInCart = _noItemsInCart.asStateFlow()

    private val getCartDataSource = GetCartDataSource()

    private var deleteFromCartDataSource = DeleteFromCartDataSource()

    private var quantityUpdateDataSource = QuantityUpdateDataSource()


    fun loadCartItems() {
        viewModelScope.launch {
            val cart = getCartDataSource.getItemsInCart()
            _cartProducts.update { cart }
            _noItemsInCart.update { cart.isEmpty() }
        }
    }

    fun getTotalPrice(quantityProduct: List<QuantityProduct>): Int {
        return quantityProduct.sumOf { it.quantity * it.product.price }
    }

    fun deleteFromCart(quantityProduct: QuantityProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromCartDataSource.deleteProductFromCart(quantityProduct.product.id)
            loadCartItems()
        }
    }

    fun increment(quantityProduct: QuantityProduct){
        viewModelScope.launch(Dispatchers.IO) {
            quantityUpdateDataSource.quantityUpdate(productId = quantityProduct.product.id, isIncrement = true)
            loadCartItems()
        }
    }

    fun decrement(quantityProduct: QuantityProduct){
        viewModelScope.launch(Dispatchers.IO) {
            quantityUpdateDataSource.quantityUpdate(productId = quantityProduct.product.id, isIncrement = false)
            loadCartItems()
        }
    }


}
