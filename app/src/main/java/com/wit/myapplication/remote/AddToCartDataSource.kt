package com.wit.myapplication.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wit.myapplication.model.CartResponse
import com.wit.myapplication.ui.main.cart.Cart
import kotlinx.coroutines.tasks.await


class AddToCartDataSource {
    suspend fun addProductToCart(productId: String) {
        val database =
            FirebaseDatabase.getInstance("https://voguely-2512-default-rtdb.europe-west1.firebasedatabase.app/")
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val cartRef = database.getReference("carts").child(userId)

        var carts = cartRef.get().await().children.mapNotNull {
            var cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
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
            newItem.setValue(CartResponse(productId, 1))
                .await()
        }
    }
}


       /* val existingItem = cartRef.equalTo(productId)
        existingItem.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val item = snapshot.children.first().key
                    val currentAmount = snapshot.children.first().child("quantity").value as Long
                    if (productId != null) {
                        cartRef
                            .child(productId).child("quantity").setValue(currentAmount + 1)
                    }
                }
                else {
                    val newItem = cartRef.push()
                    newItem.setValue(CartResponse(productId, 1))

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })*/

