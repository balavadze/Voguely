package com.wit.myapplication.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.myapplication.databinding.GridViewBinding

class ProductAdapter(val onImageClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {
    var data: MutableList<Product> = mutableListOf()


    class ItemViewHolder(val binding: GridViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = GridViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val product = data.get(position)
        holder.binding.productDescription.text = product.productDescription
        holder.binding.productPrice.text = product.price
        holder.binding.productRating.text = product.rating
        holder.binding.productReview.text = product.review

        Glide.with(holder.itemView.context)
            .load(product.productPhoto)
            .into(holder.binding.product)
        holder.itemView.setOnClickListener {
            onImageClick(product)
        }
    }

    override fun getItemCount(): Int = data.size
}

