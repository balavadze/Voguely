package com.wit.myapplication.ui.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.wit.myapplication.databinding.ProductDetailsBinding
import com.wit.myapplication.model.Product
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductDetailsFragment : Fragment() {
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var binding: ProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = ProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("PRODUCT_ID_ARG")?.let { viewModel.loadProducts(it) }

        lifecycleScope.launch {
            viewModel.product.collectLatest { product ->
                if (product != null) {
                    binding.productName.text = product.name
                    binding.productRating.text = product.rating.toString()
                    binding.productPrice.text = product.price.toString()

                    binding.productDetailedDescription.text = product.description

                    Glide.with(requireContext())
                        .load(product.image)
                        .into(binding.productImage)
                }
            }
        }

        binding.addToCartButton.setOnClickListener {
            viewModel.addToCart()
        }
    }


    private fun product(product: Product) {
        //add product to cart
        // cart.addProduct(product)

    }
}
