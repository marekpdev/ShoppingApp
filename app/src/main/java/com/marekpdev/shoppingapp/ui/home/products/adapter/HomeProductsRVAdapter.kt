package com.marekpdev.shoppingapp.ui.home.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl
import com.marekpdev.shoppingapp.ui.favourite.ProductGridVH
import com.marekpdev.shoppingapp.utils.EmptyVH
import com.marekpdev.shoppingapp.utils.RVDisplayableItem

/**
 * Created by Marek Pszczolka on 09/02/2022.
 */
class HomeProductsRVAdapter(private val products: List<Product>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        private const val VH_TYPE_EMPTY = 0
        private const val VH_TYPE_PRODUCT = 1
        private const val VH_TYPE_SHOW_MORE = 2
    }

    private val items = mutableListOf<RVDisplayableItem>().apply {
        products.forEach { add(HomeProductDisplayableItem(it)) }
        add(ShowMoreDisplayableItem())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VH_TYPE_PRODUCT -> ProductGridVH(
                view = layoutInflater.inflate(ProductGridVH.layout, parent, false),
            )
            VH_TYPE_SHOW_MORE -> HomeShowMoreVH(
                view = layoutInflater.inflate(HomeShowMoreVH.layout, parent, false)
            )
            else -> EmptyVH(
                view = layoutInflater.inflate(EmptyVH.layout, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            VH_TYPE_PRODUCT -> {
                val product = (items[position] as HomeProductDisplayableItem).product
                (holder as ProductGridVH).bind(product)
            }
            VH_TYPE_SHOW_MORE -> {
                val header = (items[position] as ShowMoreDisplayableItem)
                (holder as HomeShowMoreVH).bind()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is HomeProductDisplayableItem -> VH_TYPE_PRODUCT
            is ShowMoreDisplayableItem -> VH_TYPE_SHOW_MORE
            else -> VH_TYPE_EMPTY
        }
    }

}