package com.wit.myapplication.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.myapplication.DATABASE_URL
import com.wit.myapplication.model.Product
import kotlinx.coroutines.tasks.await

class ProductsDataSource {

    suspend fun getProducts(): List<Product> {
        val result = Firebase.database(DATABASE_URL)
                .getReference("products")
                .get()
                .await()

        return result.children.mapNotNull { it.getValue(Product::class.java) }
    }
}
