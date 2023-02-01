package com.wit.myapplication.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wit.myapplication.DATABASE_URL
import com.wit.myapplication.model.CartResponse
import com.wit.myapplication.model.QuantityProduct
import kotlinx.coroutines.tasks.await

class GetCartDataSource {

    private val productDataSource = ProductDataSource()

    suspend fun getItemsInCart(): List<QuantityProduct> {
        val database = Firebase.database(DATABASE_URL)
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return emptyList()

        val cartRef = database.getReference("carts").child(userId)
            .get()
            .await()

        return cartRef.children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val product =
                productDataSource.getProductId(cartResponse.productId) ?: return@mapNotNull null
            QuantityProduct(cartResponse.quantity, product)
        }
    }
}

