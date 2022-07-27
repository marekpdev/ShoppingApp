package com.marekpdev.shoppingapp.ui.basket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhBasketTotalCostBinding
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class BasketTotalCostAdapterDelegate :
    BaseAdapterDelegate<TotalCost, BaseViewHolder<VhBasketTotalCostBinding>>(TotalCost::class.java){

    override fun bindViewHolder(item: TotalCost, holder: BaseViewHolder<VhBasketTotalCostBinding>) {
        holder.bind {
            tvTotalCost.text = "Total Cost $${item.totalCost}"
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhBasketTotalCostBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_basket_total_cost,
                parent,
                false
            )
        )
}

data class TotalCost(val totalCost: Double)