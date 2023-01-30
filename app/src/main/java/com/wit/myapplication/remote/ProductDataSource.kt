package com.wit.myapplication.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.myapplication.model.Product
import kotlinx.coroutines.tasks.await

class ProductDataSource {

    suspend fun getProductId(id: String): Product? {
        val result =
            Firebase.database("https://voguely-2512-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("products/$id")
                .get()
                .await()



        return result.getValue(Product::class.java)
    }

}