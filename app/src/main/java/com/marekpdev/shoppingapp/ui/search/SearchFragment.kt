package com.marekpdev.shoppingapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.favourite.ProductWidthConstAdapterDelegate
import com.marekpdev.shoppingapp.utils.setTextIfDifferent

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class SearchFragment : Fragment(), MviView<SearchState, SearchCommand> {

    // todo
    // need to add
    // FILTER and SORT workflows

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    private lateinit var binding: FragmentSearchBinding

    private val onProductClicked: (Product) -> Unit = {
        viewModel.dispatch(SearchAction.ProductClicked(it.id))
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
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentSearchBinding) = binding.apply {
        rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        rvProducts.adapter = adapter

        viewModel.bind(viewLifecycleOwner, this@SearchFragment)

        etSearch.doAfterTextChanged {
            viewModel.dispatch(SearchAction.SearchQueryChanged(it.toString()))
        }
    }

    override fun render(state: SearchState) {
        binding.apply {
            etSearch.setTextIfDifferent(state.searchQuery)
            adapter.replaceData(state.products)
            tvSummary.text = state.searchSummary
            pbSearch.visibility = when (state.searchInProgress) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    override fun onCommand(command: SearchCommand) {
        when(command){
            is SearchCommand.GoToProductDetailsScreen -> {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductFragment(productId = command.productId))
            }
        }
    }

}