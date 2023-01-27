package com.wit.myapplication.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wit.myapplication.model.CartResponse
import kotlinx.coroutines.tasks.await


class AddToCartDataSource {
    suspend fun addProductToCart(productId: String) {
        val database =
            FirebaseDatabase.getInstance("https://voguely-2512-default-rtdb.europe-west1.firebasedatabase.app/")
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val cartRef = database.getReference("carts").child(userId)
        val newItemRef = cartRef.push()
        newItemRef.setValue(CartResponse(productId, 1))
            .await()

    }
}