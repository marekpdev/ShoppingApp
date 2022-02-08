package com.marekpdev.shoppingapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.ui.favourite.ProductGridVH

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
        add(HomeBannerDisplayableItem())
        add(HomeProductsHeaderDisplayableItem("Best sellers2"))
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
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is HomeBannerDisplayableItem -> VH_TYPE_HOME_BANNER
            is HomeProductsHeaderDisplayableItem -> VH_TYPE_HOME_PRODUCTS_HEADER
            else -> VH_TYPE_EMPTY
        }
    }
}