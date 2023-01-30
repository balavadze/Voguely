package com.wit.myapplication.ui.main.cart

import CartAdapter
import DeleteFromCartDataSource
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wit.myapplication.databinding.FragmentCartBinding
import com.wit.myapplication.model.Other
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private val adapter = CartAdapter(::cart)
    private lateinit var viewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding
    private val deleteFromCartDataSource = DeleteFromCartDataSource()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartItem.adapter = adapter
        viewModel.loadCartItems()

        lifecycleScope.launch {
            viewModel.cartProducts.collectLatest { cart ->
                adapter.cartData = cart
                adapter.notifyDataSetChanged()
                if (cart.isNotEmpty()) {
                    binding.cartItem.visibility = View.VISIBLE

                    binding.cartRibbon.visibility = View.VISIBLE
                    binding.sumPrice.text = "EUR  " + viewModel.getTotalPrice(cart).toString()
                    binding.emptyCart.visibility = View.GONE
                }
                else {
                    binding.emptyCart.visibility = View.VISIBLE
                    binding.cartItem.visibility = View.GONE
                    binding.cartRibbon.visibility = View.GONE
                }
            }
        }

    }


}


private fun cart(cartItems: Other) {
}

