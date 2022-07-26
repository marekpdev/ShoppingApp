package com.marekpdev.shoppingapp.ui.orders.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhOrdersHeaderBinding
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class OrdersHeaderAdapterDelegate :
    BaseAdapterDelegate<String, BaseViewHolder<VhOrdersHeaderBinding>>(String::class.java){

    override fun bindViewHolder(item: String, holder: BaseViewHolder<VhOrdersHeaderBinding>) {
        holder.bind {
            tvOrderHeader.text = item
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhOrdersHeaderBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_orders_header,
                parent,
                false
            )
        )
}