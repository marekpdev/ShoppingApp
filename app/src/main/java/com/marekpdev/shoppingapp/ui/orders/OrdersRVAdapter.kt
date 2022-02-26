package com.marekpdev.shoppingapp.ui.orders

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.models.Order
import com.marekpdev.shoppingapp.models.OrderProduct
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
class OrdersRVAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        private const val VH_TYPE_EMPTY = 0
        private const val VH_TYPE_ORDERS_HEADER = 1
        private const val VH_TYPE_ORDER = 2
    }

    private val items = mutableListOf<RVDisplayableItem>().apply {
        val repo = ProductRepositoryImpl()

        val thisWeekCount = 3

        add(OrdersHeaderDisplayableItem("This week $thisWeekCount"))
        (1..thisWeekCount).forEach {
            val order = repo.createOrder(it.toLong())
            add(OrderDisplayableItem(order))
        }

        val lastWeekCount = 5

        add(OrdersHeaderDisplayableItem("Last week $lastWeekCount"))
        (1..lastWeekCount).forEach {
            val order = repo.createOrder(it.toLong())
            add(OrderDisplayableItem(order))
        }

        val lastMonthCount = 5

        add(OrdersHeaderDisplayableItem("Last month $lastMonthCount"))
        (1..lastMonthCount).forEach {
            val order = repo.createOrder(it.toLong())
            add(OrderDisplayableItem(order))
        }
        Log.d("FEO33", "Added some items")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("FEO33", "onCreateViewHolder $viewType")
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VH_TYPE_ORDERS_HEADER -> OrdersHeaderVH(
                view = layoutInflater.inflate(OrdersHeaderVH.layout, parent, false)
            )
            VH_TYPE_ORDER -> OrderVH(
                view = layoutInflater.inflate(OrderVH.layout, parent, false)
            )
            else -> EmptyVH(
                view = layoutInflater.inflate(EmptyVH.layout, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("FEO33", "onBindViewHolder $position")
        when(getItemViewType(position)){
            VH_TYPE_ORDERS_HEADER -> {
                val item = (items[position] as OrdersHeaderDisplayableItem)
                (holder as OrdersHeaderVH).bind(item.header)
            }
            VH_TYPE_ORDER -> {
                val order = (items[position] as OrderDisplayableItem).order
                (holder as OrderVH).bind(order)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("FEO33", "getItemCount ${items.size}")
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is OrdersHeaderDisplayableItem -> VH_TYPE_ORDERS_HEADER
            is OrderDisplayableItem -> VH_TYPE_ORDER
            else -> VH_TYPE_EMPTY
        }
    }
}