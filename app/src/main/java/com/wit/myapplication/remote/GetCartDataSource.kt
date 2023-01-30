package com.wit.myapplication.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.myapplication.model.CartResponse
import com.wit.myapplication.model.Other
import kotlinx.coroutines.tasks.await

class GetCartDataSource {

    private val productDataSource = ProductDataSource()

    suspend fun getItemsInCart(): List<Other> {
        val database = Firebase.database("https://voguely-2512-default-rtdb.europe-west1.firebasedatabase.app")
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return emptyList()
        val cartRef = database.getReference("carts").child(userId)
            .get()
            .await()

        Log.d("FirebaseData", "Received data: ${cartRef.children}")

        return cartRef.children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val product = productDataSource.getProductId(cartResponse.productId) ?: return@mapNotNull null
            Other(cartResponse.quantity, product)

        }
    }
}

