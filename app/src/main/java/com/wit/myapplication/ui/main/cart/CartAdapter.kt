
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.myapplication.databinding.CartItemBinding
import com.wit.myapplication.model.Other
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartAdapter(
    val onCartItemClick: (Other) -> Unit,
    // val onDeleteButtonClick: (Other) -> Unit,
) :
    RecyclerView.Adapter<CartAdapter.ItemViewHolder>() {
    var cartData: List<Other> = listOf()

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
            holder.binding.cartQuantity.text = "x " + fireData.quantity.toString()
            holder.binding.deleteButton.setOnClickListener {
                Log.d("CartAdapter", "Delete button clicked")
                GlobalScope.launch(Dispatchers.IO) {
                    DeleteFromCartDataSource().deleteProductFromCart(productId = fireData.product.id)
                    withContext(Dispatchers.Main) {
                        cartData = cartData.filter { it.product.id != fireData.product.id }
                        notifyDataSetChanged()
                    }
                }
            }
            Glide.with(holder.itemView.context)
                .load(cartItems.product.image)
                .into(holder.binding.cartProduct)
        }
    }


    override fun getItemCount(): Int = cartData.size
}
