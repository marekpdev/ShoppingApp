package com.marekpdev.shoppingapp.ui.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhOrderBinding
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class OrderAdapterDelegate(private val onOrderClicked: (Order) -> Unit) :
    BaseAdapterDelegate<Order, BaseViewHolder<VhOrderBinding>>(Order::class.java){

    override fun bindViewHolder(item: Order, holder: BaseViewHolder<VhOrderBinding>) {
        holder.bind {
            root.setOnClickListener { onOrderClicked(item) }

            tvOrderId.text = "Order Id"
            tvOrderStatus.text = "Order status"
            btnViewDetails.text = "View Details"
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhOrderBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_order,
                parent,
                false
            )
        )
}