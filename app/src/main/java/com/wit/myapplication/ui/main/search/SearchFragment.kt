package com.wit.myapplication.ui.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wit.myapplication.R
import com.wit.myapplication.databinding.FragmentSearchBinding
import com.wit.myapplication.model.Product
import com.wit.myapplication.ui.main.home.ProductAdapter
import com.wit.myapplication.ui.productdetails.ProductDetailsFragment.Companion.PRODUCT_ID_ARG
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding

    private val adapter = ProductAdapter(::onProductClick, ::onDotsClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRecycler.adapter = adapter

        lifecycleScope.launch {
            viewModel.searchedProducts.collectLatest { product ->
                adapter.data = product
                adapter.notifyDataSetChanged()
            }
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!s.isNullOrEmpty()) {
                    viewModel.search(s.toString())
                    var found = false
                    for (product in adapter.data) {
                        if (product.name.contains(s.toString(), ignoreCase = true)) {
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        binding.searchRecycler.visibility = View.GONE
                        binding.noResults.visibility = View.VISIBLE
                    }
                    else {
                        binding.searchRecycler.visibility = View.VISIBLE
                        binding.noResults.visibility = View.GONE
                    }
                }
                else {
                    binding.searchRecycler.visibility = View.GONE
                    binding.noResults.visibility = View.GONE
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

    }

    private fun onProductClick(product: Product) {
        val bundle = Bundle().apply {
            putString(PRODUCT_ID_ARG, product.id)
        }
        parentFragment
            ?.parentFragment
            ?.findNavController()
            ?.navigate(R.id.action_mainFragment_to_productDetailsFragment, bundle)
    }

    private fun onDotsClick(product: Product, view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.popup, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            viewModel.addToCart(product.id)
            return@setOnMenuItemClickListener false
        }
    }
}