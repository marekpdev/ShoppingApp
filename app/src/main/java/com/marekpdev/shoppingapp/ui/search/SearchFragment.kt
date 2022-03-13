package com.marekpdev.shoppingapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.favourite.ProductWidthConstAdapterDelegate

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class SearchFragment : Fragment() {

    // todo
    // need to add
    // FILTER and SORT workflows

    private lateinit var viewModel: SearchViewModel

    private lateinit var binding: FragmentSearchBinding

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked product")
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductFragment(1))
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
//            .addDelegate(ProductAdapterDelegate(onProductClicked))
            .addDelegate(ProductWidthConstAdapterDelegate(onProductClicked))
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

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

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
        rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        rvProducts.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.replaceData(it)
        }

        viewModel.summaryText.observe(viewLifecycleOwner) {
            tvSummary.text = it
        }

        viewModel.searchLoading.observe(viewLifecycleOwner) { loading ->
            pbSearch.visibility = when(loading){
                true -> View.VISIBLE
                else -> View.GONE
            }
        }

        etSearch.doAfterTextChanged {
            viewModel.onNewSearch(it.toString())
        }

    }

}