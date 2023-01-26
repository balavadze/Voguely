package com.wit.myapplication.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.myapplication.ui.main.home.Product
import kotlinx.coroutines.tasks.await

class ProductsDataSource {

    suspend fun getProducts(): List<Product> {
        val result =
            Firebase.database("https://voguely-2512-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("products")
                .get()
                .await()

        return result.children.mapNotNull { it.getValue(Product::class.java) }
    }
}
