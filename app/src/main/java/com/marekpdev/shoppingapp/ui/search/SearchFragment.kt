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

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    private lateinit var binding: FragmentSearchBinding

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked product")
        viewModel.onProductClicked(it)
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

        // https://developer.android.com/codelabs/kotlin-android-training-live-data#4
        // here there is some info so might need to add observers in onCreateView instead
//        Why use viewLifecycleOwner?
//        Fragment views get destroyed when a user navigates away from a fragment,
//        even though the fragment itself is not destroyed. This essentially creates
//        two lifecycles, the lifecycle of the fragment, and the lifecycle of the
//        fragment's view. Referring to the fragment's lifecycle instead of the
//        fragment view's lifecycle can cause subtle bugs when updating the fragment's
//        view. Therefore, when setting up observers that affect the fragment's view you should:
//        1. Set up the observers in onCreateView()
//        2. Pass in viewLifecycleOwner to observers

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

        viewModel.goToProductDetailsEvent.observe(viewLifecycleOwner) { product ->
            if(product != null) {
//                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductFragment(productId = product.id))
                viewModel.goToProductDetailsEventFinished()
            }
        }

        etSearch.doAfterTextChanged {
            viewModel.onNewSearch(it.toString())
        }

    }

}