package com.wit.myapplication.ui.main.search

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

class SearchViewModel : ViewModel() {


    var productsDataSource = ProductsDataSource()
    private var addToCartDataSource = AddToCartDataSource()
    private val _searchedProducts = MutableStateFlow<List<Product>>(listOf())
    val searchedProducts = _searchedProducts.asStateFlow()

    private val _noResults = MutableStateFlow(false)
    val noResults = _noResults.asStateFlow()


    fun search(s: CharSequence) {
        viewModelScope.launch {
            if (s.isBlank()) {
                _noResults.update { false }
                _searchedProducts.update { emptyList() }
                return@launch
            }


            val products = ProductsDataSource().getProducts()
            _searchedProducts.update {
                products.filter { it.name.contains(s) }
                // emptyList()
                /*.filter { it.productDescription.contains(s) }*/
            }
            _noResults.update {
                products.isEmpty()
            }

        }
    }

    fun addToCart(productId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addToCartDataSource.addProductToCart(productId)
        }
    }
}


