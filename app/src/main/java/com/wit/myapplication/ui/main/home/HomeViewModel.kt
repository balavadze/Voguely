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
                    "Headphone",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),
                Product(
                    "https://images.unsplash.com/photo-1607522370275-f14206abe5d3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1721&q=80",
                    "Shoe",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),
                Product(
                    "https://plus.unsplash.com/premium_photo-1671028365039-d9294e26e482?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8c2hvZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60",
                    "Shoe",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),
                Product(
                    "https://images.unsplash.com/photo-1633966887768-64f9a867bdba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHRzaGlydHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60",
                    "Tshirt",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),
                Product(
                    "https://images.unsplash.com/photo-1580051235428-f88ae8a2d53b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHVtYnJlbGxhfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
                    "Umbrella",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),
                Product(
                    "https://images.unsplash.com/photo-1632973541997-971a2eb8331d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTV8fHVtYnJlbGxhfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60",
                    "Umbrella",
                    "Rp. 9.99",
                    "4.6",
                    "280 Reviews",
                    "dots"
                ),
                Product(
                    "https://images.unsplash.com/photo-1609873814058-a8928924184a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTR8fGhvb2RpZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60",
                    "Hoodie",
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

