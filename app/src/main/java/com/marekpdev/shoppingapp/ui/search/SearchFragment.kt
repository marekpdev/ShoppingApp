package com.marekpdev.shoppingapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked product")
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(ProductAdapterDelegate(onProductClicked))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@SearchFragment
//            productViewModel = viewModel
//            btnLogin.setOnClickListener {
//                findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
//            }
//
//            btnRegistration.setOnClickListener {
//                findNavController().navigate(R.id.action_accountFragment_to_registrationFragment)
//            }
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentSearchBinding) = binding.apply {
        Log.d("FEO33", "initLayout")
        btnProduct.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductFragment(1))
        }

        rvProducts.layoutManager = LinearLayoutManager(context)
        rvProducts.adapter = adapter
        adapter.replaceData(items)
    }

    private val items = Data.getMenu().second

}