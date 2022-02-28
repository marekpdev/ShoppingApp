package com.marekpdev.shoppingapp.ui.paymentmethods

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.models.Order
import com.marekpdev.shoppingapp.models.OrderProduct
import com.marekpdev.shoppingapp.models.PaymentCard
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
class PaymentMethodsRVAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        private const val VH_TYPE_EMPTY = 0
        private const val VH_TYPE_PAYMENT_CARD = 1
    }

    private val items = mutableListOf<RVDisplayableItem>().apply {
        add(PaymentCardDisplayableItem(PaymentCard("VISA", "3443434343")))
        add(PaymentCardDisplayableItem(PaymentCard("VISA", "122121212")))
        add(PaymentCardDisplayableItem(PaymentCard("VISA", "654654554")))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("FEO33", "onCreateViewHolder $viewType")
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VH_TYPE_PAYMENT_CARD -> PaymentCardVH(
                view = layoutInflater.inflate(PaymentCardVH.layout, parent, false)
            )
            else -> EmptyVH(
                view = layoutInflater.inflate(EmptyVH.layout, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("FEO33", "onBindViewHolder $position")
        when(getItemViewType(position)){
            VH_TYPE_PAYMENT_CARD -> {
                val item = (items[position] as PaymentCardDisplayableItem)
                (holder as PaymentCardVH).bind(item.paymentCard)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("FEO33", "getItemCount ${items.size}")
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is PaymentCardDisplayableItem -> VH_TYPE_PAYMENT_CARD
            else -> VH_TYPE_EMPTY
        }
    }
}