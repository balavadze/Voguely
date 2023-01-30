package com.wit.myapplication.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wit.myapplication.model.Cart
import com.wit.myapplication.model.CartResponse
import kotlinx.coroutines.tasks.await
class AddToCartDataSource {
    suspend fun addProductToCart(productId: String) {
        val database =
            FirebaseDatabase.getInstance("https://voguely-2512-default-rtdb.europe-west1.firebasedatabase.app/")
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val cartRef = database.getReference("carts").child(userId)

        var carts = cartRef.get().await().children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, cartResponse)
        }

        val existingItem = carts.firstOrNull { it.cartResponse.productId == productId }

        if (existingItem != null) {
            cartRef
                .child(existingItem.key).child("quantity")
                .setValue(existingItem.cartResponse.quantity + 1)
                .await()
        }
        else {
            val newItem = cartRef.push()
            newItem.setValue(CartResponse(productId, 1, ))
                .await()
        }
    }
}

