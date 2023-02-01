import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.myapplication.databinding.CartItemBinding
import com.wit.myapplication.model.QuantityProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartAdapter(
    private val onDeleteButtonClick: (QuantityProduct) -> Unit,
    private val onIncrementClicked: (QuantityProduct) -> Unit,
    private val onDecrementClicked: (QuantityProduct) -> Unit,
) :
    RecyclerView.Adapter<CartAdapter.ItemViewHolder>() {

    var cartData: List<QuantityProduct> = listOf()

    class ItemViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cartItem = cartData[position]
        val fireData = cartData.get(position)
        holder.binding.cartDescription.text = fireData.product.name
        holder.binding.cartPrice.text = "EUR  ${fireData.product.price}"
        holder.binding.cartQuantity.text = fireData.quantity.toString()
        Glide.with(holder.itemView.context)
            .load(cartItem.product.image)
            .into(holder.binding.cartProduct)

        holder.binding.cartQuantityMinus.setOnClickListener {
            onDecrementClicked.invoke(cartItem)
        }
        holder.binding.cartQuantityPlus.setOnClickListener {
            onIncrementClicked.invoke(cartItem)
        }

        holder.binding.deleteButton.setOnClickListener {
            onDeleteButtonClick.invoke(cartItem)
        }
    }


    override fun getItemCount(): Int = cartData.size
}