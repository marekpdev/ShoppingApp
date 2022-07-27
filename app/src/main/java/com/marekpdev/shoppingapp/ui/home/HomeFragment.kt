package com.marekpdev.shoppingapp.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentHomeBinding
import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.home.banner.HomeBannerAdapterDelegate
import com.marekpdev.shoppingapp.ui.home.banner.HomeBanners
import com.marekpdev.shoppingapp.ui.home.products.HomeProducts
import com.marekpdev.shoppingapp.ui.home.products.HomeProductsAdapterDelegate
import com.marekpdev.shoppingapp.ui.home.productsheader.HomeProductsHeader
import com.marekpdev.shoppingapp.ui.home.productsheader.HomeProductsHeaderAdapterDelegate
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeState, HomeAction, HomeCommand, FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel by viewModels<HomeViewModel>()

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
            .addDelegate(HomeProductsHeaderAdapterDelegate(onShowMoreClicked))
            .addDelegate(HomeProductsAdapterDelegate(onProductClicked, onToggleFavourite))
    )

    override fun initLayout(binding: FragmentHomeBinding) = with(binding) {
        rvHomeContent.layoutManager = LinearLayoutManager(context)
        rvHomeContent.adapter = adapter
    }

    override fun render(state: HomeState) {
        binding.apply {

            val items = mutableListOf<Any>().apply {

                add(HomeBanners(state.homeBanners))

                // todo
                // need to fix issue: when product is favoured/unfavoured then the list jumps back
                // to the beginning
                state.productRecommendations.forEach { (category, products) ->
                    add(HomeProductsHeader(category))
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