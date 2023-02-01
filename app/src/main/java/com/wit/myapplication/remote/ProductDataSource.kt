package com.wit.myapplication.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.myapplication.DATABASE_URL
import com.wit.myapplication.model.Product
import kotlinx.coroutines.tasks.await

class ProductDataSource {

    suspend fun getProductId(id: String): Product? {
        val result =
            Firebase.database(DATABASE_URL)
                .getReference("products/$id")
                .get()
                .await()

        return result.getValue(Product::class.java)
    }

}