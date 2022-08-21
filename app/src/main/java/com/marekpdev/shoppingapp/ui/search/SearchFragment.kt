package com.marekpdev.shoppingapp.ui.search

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.children
import androidx.core.view.doOnLayout
import androidx.core.view.get
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.navigation.OnBackPressedCallback
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.productvh.ProductWidthConstAdapterDelegate
import com.marekpdev.shoppingapp.ui.search.filter.FilterBottomSheet
import com.marekpdev.shoppingapp.ui.search.sort.SortBottomSheet
import com.marekpdev.shoppingapp.utils.setTextIfDifferent
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchState, SearchAction, SearchCommand, FragmentSearchBinding>(R.layout.fragment_search),
    OnBackPressedCallback {

    override val viewModel by viewModels<SearchViewModel>()

    private val onProductClicked: (Product) -> Unit = {
        viewModel.dispatch(SearchAction.ProductClicked(it.id))
    }

    private val onToggleFavourite: (Product) -> Unit = {
        viewModel.dispatch(SearchAction.ToggleFavouriteClicked(it))
    }

    private val onCategoryClicked: (Category) -> Unit = {
        viewModel.dispatch(SearchAction.CategoryClicked(it))
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
//            .addDelegate(ProductAdapterDelegate(onProductClicked, onToggleFavourite))
            .addDelegate(ProductWidthConstAdapterDelegate(onProductClicked, onToggleFavourite))
            .addDelegate(MenuCategoryDelegate(onCategoryClicked))
    )

    override fun initLayout(binding: FragmentSearchBinding) = with(binding) {
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

        ivSort.clipToOutline = true
        ivFilter.clipToOutline = true
        
        ivSort.setOnClickListener { viewModel.dispatch(SearchAction.SortClicked) }
        ivFilter.setOnClickListener { viewModel.dispatch(SearchAction.FilterClicked) }

        etSearch.doAfterTextChanged {
            viewModel.dispatch(SearchAction.SearchQueryChanged(it.toString()))
        }

        // TODO need to use some different scope function so i don't need to
        // explicitly say 'return@with' - maybe create my own?
        return@with
    }

    override fun render(state: SearchState) {
        binding.apply {
            etSearch.setTextIfDifferent(state.searchQuery)

            val newData = mutableListOf<Any>().apply {
                addAll(state.menu.categories)
                addAll(state.menu.products)
            }
            adapter.replaceData(newData)
            tvSummary.text = state.searchSummary
            tvSummary.visibility = if(state.searchSummary.isNotBlank()) View.VISIBLE else View.GONE
            pbSearch.visibility = when (state.searchInProgress) {
                true -> View.VISIBLE
                else -> View.GONE
            }

            val tabs = listOf("Text", "Text2ffe", "Text3f", "Text4")
            tabLayoutCategories.removeAllTabs()
            tabs.forEach { tabLabel ->
                tabLayoutCategories.addTab(tabLayoutCategories.newTab().setText(tabLabel))
            }

            // TODO remove?
//            val child = tabLayoutCategories[0] as ViewGroup
//            tabLayoutCategories.requestLayout()
//            child.requestLayout()
//            tabLayoutCategories.invalidate()
//            child.invalidate()
//            tabLayoutCategories.viewTreeObserver.dispatchOnGlobalLayout()
//            child.viewTreeObserver.dispatchOnGlobalLayout()
//            tabLayoutCategories.stretch()
        }
    }

    override fun onCommand(command: SearchCommand) {
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
        return if(viewModel.canHandleBackPressed()){
            viewModel.dispatch(SearchAction.BackPressed)
            true
        } else {
            false
        }
    }
}