
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.myapplication.databinding.CartItemBinding
import com.wit.myapplication.model.Cart
import com.wit.myapplication.model.Other
import kotlin.reflect.KFunction1

class CartAdapter(val onCartItemClick: KFunction1<Cart, Unit>) :
    RecyclerView.Adapter<CartAdapter.ItemViewHolder>() {
    var cartData: List<Other> = listOf()
    var totalPrice: Double = 0.0

    class ItemViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ItemViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartAdapter.ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ItemViewHolder, position: Int) {
        val cartItems = cartData.get(position)
        if (cartData.isNotEmpty()) {
            val fireData = cartData.get(position)
            holder.binding.cartDescription.text = fireData.product.name.toString()
            holder.binding.cartPrice.text = "EUR  " + fireData.product.price.toString()
            holder.binding.cartAmount.text = "x " + fireData.quantity.toString()
            Glide.with(holder.itemView.context)
                .load(cartItems.product.image)
                .into(holder.binding.cartProduct)
        }
    }

    override fun getItemCount(): Int = cartData.size
}
