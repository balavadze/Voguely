package com.wit.myapplication.ui.main.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wit.myapplication.databinding.GridViewBinding


class ProductAdapter(val onProductClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ItemViewHolder>() {
    var data: List<Product> = listOf()

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
        holder.binding.productDescription.text = product.name
        holder.binding.productPrice.text = product.price.toString()

        holder.binding.productRating.text = product.rating.toString()
        holder.binding.productReview.text = product.reviews.toString()

        Glide.with(holder.itemView.context)
            .load(product.image)
            .into(holder.binding.product)
        holder.itemView.setOnClickListener {
            onProductClick(product)
            Log.d("TAG", "onProductClick called for product: ${product.name}")

        }
    }


    /*inner class searchFilter(private val adapter: ProductAdapter) : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = products.filter { it.name.contains(constraint.toString(), true) }
            val results = FilterResults()
            results.values = filteredList
            return results
        }
    }*/


    override fun getItemCount(): Int = data.size
}

