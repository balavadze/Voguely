package com.wit.myapplication.ui.main.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wit.myapplication.R
import com.wit.myapplication.databinding.FragmentHomeBinding
import com.wit.myapplication.model.Product
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {


    private val adapter = ProductAdapter(::onProductClick)
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.product.collectLatest { product ->
                adapter.data = product
                adapter.notifyDataSetChanged()
            }
        }

    }

    private fun onProductClick(product: Product) {
        val bundle = Bundle().apply {
            putString("PRODUCT_ID_ARG", product.id)
        }
        parentFragment
            ?.parentFragment
            ?.findNavController()
            ?.navigate(R.id.action_mainFragment_to_productDetailsFragment, bundle)
    }

}




