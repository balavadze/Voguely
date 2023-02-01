import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wit.myapplication.DATABASE_URL
import com.wit.myapplication.model.Cart
import com.wit.myapplication.model.CartResponse
import kotlinx.coroutines.tasks.await

class QuantityUpdateDataSource {

    suspend fun quantityUpdate(productId: String, isIncrement: Boolean) {
        val database = FirebaseDatabase.getInstance(DATABASE_URL)
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val cartRef = database.getReference("carts").child(userId)
        val carts = cartRef.get().await().children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, cartResponse)
        }

        val quantityPlus = carts.firstOrNull { it.cartResponse.productId == productId }
        quantityPlus?.let {
            cartRef
                .child(quantityPlus.key).child("quantity")
                .setValue(quantityPlus.cartResponse.quantity + (if (isIncrement) 1 else -1))
                .await()
        }
    }

}
