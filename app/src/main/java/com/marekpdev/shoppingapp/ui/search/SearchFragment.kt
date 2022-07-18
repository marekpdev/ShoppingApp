package com.marekpdev.shoppingapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.navigation.OnBackPressedCallback
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.productvh.ProductWidthConstAdapterDelegate
import com.marekpdev.shoppingapp.ui.search.filter.FilterBottomSheet
import com.marekpdev.shoppingapp.ui.search.sort.SortBottomSheet
import com.marekpdev.shoppingapp.utils.setTextIfDifferent
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class SearchFragment : Fragment(), MviView<SearchState, SearchCommand>, OnBackPressedCallback {

    // todo
    // need to add
    // FILTER and SORT workflows

    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var binding: FragmentSearchBinding

    private val onProductClicked: (Product) -> Unit = {
        viewModel.dispatch(SearchAction.ProductClicked(it.id))
    }

    private val onToggleFavourite: (Product) -> Unit = {
        viewModel.dispatch(SearchAction.ToggleFavouriteClicked(it))
    }

    private val onCategoryClicked: (Category) -> Unit = {
        viewModel.dispatch(SearchAction.CategoryClicked(it.id))
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
//            .addDelegate(ProductAdapterDelegate(onProductClicked, onToggleFavourite))
            .addDelegate(ProductWidthConstAdapterDelegate(onProductClicked, onToggleFavourite))
            .addDelegate(MenuCategoryDelegate(onCategoryClicked))
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
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItems()[position]){
                    is Category -> 2
                    is Product -> 1
                    else -> 1
                }
            }
        }

        rvProducts.layoutManager = layoutManager
        rvProducts.adapter = adapter

        ivSort.setOnClickListener { viewModel.dispatch(SearchAction.SortClicked) }
        ivFilter.setOnClickListener { viewModel.dispatch(SearchAction.FilterClicked) }

        viewModel.bind(viewLifecycleOwner, this@SearchFragment)

        etSearch.doAfterTextChanged {
            Log.d("FEO150", "Text ${it.toString()}")
            viewModel.dispatch(SearchAction.SearchQueryChanged(it.toString()))
        }
    }

    override fun render(state: SearchState) {
        Log.d("FEO410", "Render")
        binding.apply {
            etSearch.setTextIfDifferent(state.searchQuery)

            val newData = mutableListOf<Any>().apply {
                addAll(state.menu.categories)
                addAll(state.menu.products)
            }
            adapter.replaceData(newData)
            tvSummary.text = state.searchSummary
            pbSearch.visibility = when (state.searchInProgress) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    override fun onCommand(command: SearchCommand) {
        Log.d("FEO700", "Command $command")
        when(command){
            is SearchCommand.GoToProductDetailsScreen -> {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductFragment(productId = command.productId))
            }
            is SearchCommand.ShowSortBottomSheet -> {
                SortBottomSheet().show(parentFragmentManager, SortBottomSheet.TAG)
            }
            is SearchCommand.ShowFilterBottomSheet -> {
                FilterBottomSheet().show(parentFragmentManager, FilterBottomSheet.TAG)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        viewModel.dispatch(SearchAction.BackPressed)
        // todo need to handle workflow to quit the app when we are in root category already
        return true
    }
}