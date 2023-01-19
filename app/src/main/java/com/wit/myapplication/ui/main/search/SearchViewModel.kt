package com.wit.myapplication.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.myapplication.ui.main.home.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _searchedProducts = MutableStateFlow<List<Product>>(listOf())
    val searchedProducts = _searchedProducts.asStateFlow()

    fun search(s: CharSequence) {
        viewModelScope.launch {
            _searchedProducts.update { listOf<Product>().filter { it.productDescription.contains(s) } }
        }
    }


}