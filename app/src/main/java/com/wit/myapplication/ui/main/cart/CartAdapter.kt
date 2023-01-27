package com.wit.myapplication.ui.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wit.myapplication.databinding.CartItemBinding


class CartAdapter(val onCartItemClick: (Cart) -> Unit) :
    RecyclerView.Adapter<CartAdapter.ItemViewHolder>() {
    var cartData: List<Cart> = listOf()

    class ItemViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ItemViewHolder {
        val binding = CartItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartAdapter.ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ItemViewHolder, position: Int) {
        val cartItems = cartData[position]
       /* holder.binding.cartDescription.text = cartItems.cartDescription
        holder.binding.cartPrice.text = cartItems.cartPrice
        holder.binding.cartAmount.text = cartItems.amount
        Glide.with(holder.itemView.context).load(cartItems.cartPhoto)
            .into(holder.binding.cartProduct)
        holder.itemView.setOnClickListener {
            onCartItemClick(cartItems)
        }*/
    }

    override fun getItemCount(): Int = cartData.size
}
