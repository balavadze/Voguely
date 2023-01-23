package com.wit.myapplication.ui.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.myapplication.databinding.GridViewBinding
import com.wit.myapplication.ui.main.home.Product

class CartAdapter(val onProductClick: (Product) -> Unit) :
    RecyclerView.Adapter<CartAdapter.ItemViewHolder>() {
    var data: List<Product> = listOf()

    class ItemViewHolder(val binding: GridViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ItemViewHolder {
        val binding = GridViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CartAdapter.ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ItemViewHolder, position: Int) {
        val product = data.get(position)
        holder.binding.productDescription.text = product.productDescription
        holder.binding.productPrice.text = product.price
        Glide.with(holder.itemView.context).load(product.productPhoto).into(holder.binding.product)
        holder.itemView.setOnClickListener {
            onProductClick(product)
        }
    }

    override fun getItemCount(): Int = data.size
}