package com.marekpdev.shoppingapp.ui.checkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhBasketContinueCheckoutBinding
import com.marekpdev.shoppingapp.databinding.VhCheckoutPlaceOrderBinding
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class CheckoutPlaceOrderAdapterDelegate(private val onPlaceOrderClicked: () -> Unit) :
    BaseAdapterDelegate<PlaceOrder, BaseViewHolder<VhCheckoutPlaceOrderBinding>>(PlaceOrder::class.java){

    override fun bindViewHolder(item: PlaceOrder, holder: BaseViewHolder<VhCheckoutPlaceOrderBinding>) {
        holder.bind {
            btnPlaceOrder.setOnClickListener { onPlaceOrderClicked() }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhCheckoutPlaceOrderBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_checkout_place_order,
                parent,
                false
            )
        )
}

object PlaceOrder