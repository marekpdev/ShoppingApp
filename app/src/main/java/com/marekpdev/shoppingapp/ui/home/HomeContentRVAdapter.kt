package com.marekpdev.shoppingapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl
import com.marekpdev.shoppingapp.ui.home.banner.HomeBannerDisplayableItem
import com.marekpdev.shoppingapp.ui.home.banner.HomeBannerVH
import com.marekpdev.shoppingapp.ui.home.products.HomeProductsDisplayableItem
import com.marekpdev.shoppingapp.ui.home.products.HomeProductsVH
import com.marekpdev.shoppingapp.ui.home.products.adapter.HomeProductDisplayableItem
import com.marekpdev.shoppingapp.ui.home.productsheader.HomeProductsHeaderDisplayableItem
import com.marekpdev.shoppingapp.ui.home.productsheader.HomeProductsHeaderVH
import com.marekpdev.shoppingapp.utils.EmptyVH
import com.marekpdev.shoppingapp.utils.RVDisplayableItem

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeContentRVAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        private const val VH_TYPE_EMPTY = 0
        private const val VH_TYPE_HOME_BANNER = 1
        private const val VH_TYPE_HOME_PRODUCTS_HEADER = 2
        private const val VH_TYPE_HOME_PRODUCTS = 3
    }

    private val items = mutableListOf<RVDisplayableItem>().apply {
        val repo = ProductRepositoryImpl()

        add(HomeBannerDisplayableItem())
        add(HomeProductsHeaderDisplayableItem("Best sellers"))
        add(HomeProductsDisplayableItem(repo.getProducts(5)))
        add(HomeProductsHeaderDisplayableItem("Just arrived"))
        add(HomeProductsDisplayableItem(repo.getProducts(8)))
        add(HomeProductsHeaderDisplayableItem("Discover more"))
        add(HomeProductsDisplayableItem(repo.getProducts(4)))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VH_TYPE_HOME_BANNER -> HomeBannerVH(
                view = layoutInflater.inflate(HomeBannerVH.layout, parent, false),
            )
            VH_TYPE_HOME_PRODUCTS_HEADER -> HomeProductsHeaderVH(
                view = layoutInflater.inflate(HomeProductsHeaderVH.layout, parent, false)
            )
            VH_TYPE_HOME_PRODUCTS -> HomeProductsVH(
                view = layoutInflater.inflate(HomeProductsVH.layout, parent, false)
            )
            else -> EmptyVH(
                view = layoutInflater.inflate(EmptyVH.layout, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            VH_TYPE_HOME_BANNER -> {
                //val item = (items[position] as HomeBannerDisplayableItem)
                (holder as HomeBannerVH).bind()
            }
            VH_TYPE_HOME_PRODUCTS_HEADER -> {
                val header = (items[position] as HomeProductsHeaderDisplayableItem).header
                (holder as HomeProductsHeaderVH).bind(header)
            }
            VH_TYPE_HOME_PRODUCTS -> {
                val products = (items[position] as HomeProductsDisplayableItem).products
                (holder as HomeProductsVH).bind(products)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is HomeBannerDisplayableItem -> VH_TYPE_HOME_BANNER
            is HomeProductsHeaderDisplayableItem -> VH_TYPE_HOME_PRODUCTS_HEADER
            is HomeProductsDisplayableItem -> VH_TYPE_HOME_PRODUCTS
            else -> VH_TYPE_EMPTY
        }
    }
}