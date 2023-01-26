package com.wit.myapplication.ui.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.myapplication.remote.ProductDataSource
import com.wit.myapplication.ui.main.home.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {
    var productDataSource = ProductDataSource()
    var product = MutableStateFlow<Product?>(null)


    fun loadProducts(id: String) {
        viewModelScope.launch {
            product.update { productDataSource.getProductId(id) }
        }
    }

}
