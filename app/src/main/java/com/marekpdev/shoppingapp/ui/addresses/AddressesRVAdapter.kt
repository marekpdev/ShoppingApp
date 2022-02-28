package com.marekpdev.shoppingapp.ui.addresses

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.models.Address
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
class AddressesRVAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        private const val VH_TYPE_EMPTY = 0
        private const val VH_ADDRESS = 1
    }

    private val items = mutableListOf<RVDisplayableItem>().apply {
        (1..5).forEach {
            add(AddressDisplayableItem(Address("line1 - $it", "line2", "postcode", "city", "country")))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("FEO33", "onCreateViewHolder $viewType")
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VH_ADDRESS -> AddressVH(
                view = layoutInflater.inflate(AddressVH.layout, parent, false)
            )
            else -> EmptyVH(
                view = layoutInflater.inflate(EmptyVH.layout, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("FEO33", "onBindViewHolder $position")
        when(getItemViewType(position)){
            VH_ADDRESS -> {
                val item = (items[position] as AddressDisplayableItem)
                (holder as AddressVH).bind(item.address)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("FEO33", "getItemCount ${items.size}")
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is AddressDisplayableItem -> VH_ADDRESS
            else -> VH_TYPE_EMPTY
        }
    }
}