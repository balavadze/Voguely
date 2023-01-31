import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.wit.myapplication.model.Cart
import com.wit.myapplication.model.CartResponse
import kotlinx.coroutines.tasks.await

class QuantityAdjustment {
    suspend fun quantityAdjustment(productId: String, isIncrement: Boolean) {
        val database =
            FirebaseDatabase.getInstance("https://voguely-2512-default-rtdb.europe-west1.firebasedatabase.app/")
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val cartRef = database.getReference("carts").child(userId)

        var carts = cartRef.get().await().children.mapNotNull {
            val cartResponse = it.getValue(CartResponse::class.java) ?: return@mapNotNull null
            val key = it.key ?: return@mapNotNull null
            Cart(key, cartResponse)
        }

        val quantityPlus = carts.firstOrNull { it.cartResponse.productId == productId }
        val quantityMinus = carts.firstOrNull { it.cartResponse.productId == productId }

        if (isIncrement) {
            quantityPlus?.let {
                cartRef
                    .child(quantityPlus.key).child("quantity")
                    .setValue(quantityPlus.cartResponse.quantity + 1)
                    .await()
            }
        }
        else {
            quantityMinus?.let {
                cartRef
                    .child(quantityMinus.key).child("quantity")
                    .setValue(quantityMinus.cartResponse.quantity + 1)
                    .await()
            }
        }
    }
}
