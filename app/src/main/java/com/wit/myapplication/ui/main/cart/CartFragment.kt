package com.wit.myapplication.ui.main.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wit.myapplication.databinding.FragmentCartBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private val adapter = CartAdapter(::cart)
    private lateinit var viewModel: CartViewModel
    private lateinit var binding: FragmentCartBinding


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

        lifecycleScope.launch {
            viewModel.cartProducts.collectLatest { cart: List<Cart> ->
                adapter.cartData = cart
                adapter.notifyDataSetChanged()
                if (cart.isEmpty()) {
                    //TODO uncomment this line after data is added
                    // binding.emptyCart.visibility = View.VISIBLE
                }
                else {
                    binding.cartItem.visibility = View.VISIBLE
                    binding.cartRibbon.visibility = View.VISIBLE
                    binding.emptyCart.visibility = View.GONE
                    // update cart
                    // viewModel.updateCart()
                }

            }
        }
    }

    private fun cart(cartItems: Cart) {
/*        Cart.removeProduct(product)
        viewModel.updateCart()*/
    }

}