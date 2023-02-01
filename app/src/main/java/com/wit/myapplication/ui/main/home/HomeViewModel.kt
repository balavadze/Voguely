package com.wit.myapplication.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.myapplication.model.Product
import com.wit.myapplication.remote.AddToCartDataSource
import com.wit.myapplication.remote.ProductsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var productsDataSource = ProductsDataSource()
    private var addToCartDataSource = AddToCartDataSource()

    var _product = MutableStateFlow<List<Product>>(emptyList())
    var product = _product.asStateFlow()


    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _product.update { productsDataSource.getProducts() }
        }
    }


    fun addToCart(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addToCartDataSource.addProductToCart(productId)
        }
    }


}

