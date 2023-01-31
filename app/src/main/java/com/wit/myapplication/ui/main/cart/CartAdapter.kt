
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
    // val onDeleteButtonClick: (com.wit.myapplication.model.Other) -> Unit,
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
            holder.binding.cartQuantity.text = fireData.quantity.toString()
            holder.binding.cartQuantityMinus.setOnClickListener {
                Log.d("CartAdapter", "Minus button clicked")
                GlobalScope.launch(Dispatchers.IO) {
                    QuantityUpdate().quantityUpdate(
                        productId = fireData.product.id, isIncrement = false
                    )
                    withContext(Dispatchers.Main) {
                        holder.binding.cartQuantity.text = fireData.quantity.toString()

                    }
                }
            }
            holder.binding.cartQuantityPlus.setOnClickListener {
                Log.d("CartAdapter", "Plus button clicked")
                GlobalScope.launch(Dispatchers.IO) {
                    QuantityUpdate().quantityUpdate(
                        productId = fireData.product.id, isIncrement = true
                    )
                    withContext(Dispatchers.Main) {
                        holder.binding.cartQuantity.text = fireData.quantity.toString()

                    }
                }
            }

            holder.binding.deleteButton.setOnClickListener(View.OnClickListener {
                Log.d("CartAdapter", "Delete button clicked")
                GlobalScope.launch(Dispatchers.IO) {
                    DeleteFromCartDataSource().deleteProductFromCart(productId = fireData.product.id)
                    withContext(Dispatchers.Main) {
                        cartData = cartData.filter { it.product.id != fireData.product.id }

                    }
                }
            })

            Glide.with(holder.itemView.context).load(cartItems.product.image)
                .into(holder.binding.cartProduct)
        }
    }


    override fun getItemCount(): Int = cartData.size
}
