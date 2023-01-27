package com.wit.myapplication.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.myapplication.model.Product
import com.wit.myapplication.remote.AddToCartDataSource
import com.wit.myapplication.remote.ProductsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var productsDataSource = ProductsDataSource()
    private var addToCartDataSource = AddToCartDataSource()
    var product = MutableStateFlow<List<Product>>(emptyList())


    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            product.update { productsDataSource.getProducts() }

        }
    }

    fun fetchProduct(): MutableList<Product> {
        val product = mutableListOf<Product>()
        viewModelScope.launch {
        }
        // code to fetch data
        return product
    }

    fun addToCart(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addToCartDataSource.addProductToCart(productId)
        }
    }


}

