package com.wit.myapplication.ui.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wit.myapplication.model.Product
import com.wit.myapplication.remote.AddToCartDataSource
import com.wit.myapplication.remote.ProductDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {

    private var productDataSource = ProductDataSource()
    private var addToCartDataSource = AddToCartDataSource()
    var product = MutableStateFlow<Product?>(null)


    fun loadProducts(id: String) {
        viewModelScope.launch {
            product.update { productDataSource.getProductId(id) }
        }
    }

    fun addToCart() {
        viewModelScope.launch(Dispatchers.IO) {
            product.value?.let {
                addToCartDataSource.addProductToCart(it.id)
            }
        }
    }
}
