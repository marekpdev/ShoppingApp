package com.marekpdev.shoppingapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentHomeBinding
import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.home.banner.HomeBannerAdapterDelegate
import com.marekpdev.shoppingapp.ui.home.banner.HomeBannerImages
import com.marekpdev.shoppingapp.ui.home.products.HomeProducts
import com.marekpdev.shoppingapp.ui.home.products.HomeProductsAdapterDelegate
import com.marekpdev.shoppingapp.ui.home.productsheader.HomeProductsHeader
import com.marekpdev.shoppingapp.ui.home.productsheader.HomeProductsHeaderAdapterDelegate
import com.marekpdev.shoppingapp.ui.search.SearchAction
import com.marekpdev.shoppingapp.ui.search.SearchCommand
import com.marekpdev.shoppingapp.ui.search.SearchState
import com.marekpdev.shoppingapp.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class HomeFragment : Fragment(), MviView<HomeState, HomeCommand> {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding

    private val onProductClicked: (Product) -> Unit = {
        viewModel.dispatch(HomeAction.ProductClicked(it.id))
    }

    private val onShowMoreClicked: (Category) -> Unit = {
        viewModel.dispatch(HomeAction.ShowMoreClicked(it.id))
    }

    private val onToggleFavourite: (Product) -> Unit = {
        viewModel.dispatch(HomeAction.ToggleFavouriteClicked(it))
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(HomeBannerAdapterDelegate {})
            .addDelegate(HomeProductsHeaderAdapterDelegate {})
            .addDelegate(HomeProductsAdapterDelegate(onProductClicked, onToggleFavourite))
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        binding.apply {
            lifecycleOwner = this@HomeFragment
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentHomeBinding) = binding.apply {
        rvHomeContent.layoutManager = LinearLayoutManager(context)
        rvHomeContent.adapter = adapter

        viewModel.bind(viewLifecycleOwner, this@HomeFragment)
    }

    override fun render(state: HomeState) {
        binding.apply {

            Log.d("FEO999", "Rendering: ${state.productRecommendations.size}")
            val items = mutableListOf<Any>().apply {
                add(HomeBannerImages(listOf(
                    R.drawable.home_banner_1,
                    R.drawable.home_banner_2,
                    R.drawable.home_banner_3))
                )

                state.productRecommendations.forEach { (category, products) ->
                    add(HomeProductsHeader(category.name))
                    add(HomeProducts(products))
                }
            }

            adapter.replaceData(items)
        }
    }

    override fun onCommand(command: HomeCommand) {
        when(command){
            is HomeCommand.GoToProductDetailsScreen -> {

            }
        }
    }

}