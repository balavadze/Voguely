package com.wit.myapplication.ui.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wit.myapplication.databinding.FragmentSearchBinding
import com.wit.myapplication.ui.main.home.Product
import com.wit.myapplication.ui.main.home.ProductAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private val adapter = ProductAdapter(::product)
    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding

    private var products = listOf<Product>()


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
                if (product.isEmpty()) {

                } else {
                    binding.searchRecycler.visibility = View.VISIBLE
                    binding.noResults.visibility = View.GONE
                }
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
                        if (product.productDescription.contains(s.toString(), ignoreCase = true)) {
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        binding.searchRecycler.visibility = View.GONE
                        binding.noResults.visibility = View.VISIBLE
                    } else {
                        binding.searchRecycler.visibility = View.VISIBLE
                        binding.noResults.visibility = View.GONE
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

    }

    private fun product(product: Product) {
    }


}