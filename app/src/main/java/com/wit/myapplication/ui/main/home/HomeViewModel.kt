package com.wit.myapplication.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var product = MutableStateFlow<List<Product>>(emptyList())

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            val mockData = listOf(
                Product(
                    "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
                    "this is product",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),
                Product(
                    "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
                    "this is product",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),
                Product(
                    "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80",
                    "this is product",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),

                )
            product.update { mockData }
        }
    }

    fun fetchProduct(): MutableList<Product> {
        val product = mutableListOf<Product>()
        viewModelScope.launch {
        }
        // code to fetch data
        return product
    }

}

