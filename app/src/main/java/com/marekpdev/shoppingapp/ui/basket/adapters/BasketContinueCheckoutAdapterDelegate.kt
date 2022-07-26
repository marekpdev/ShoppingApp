package com.marekpdev.shoppingapp.ui.basket.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhBasketContinueCheckoutBinding
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class BasketContinueCheckoutAdapterDelegate(private val onContinueCheckoutClicked: () -> Unit) :
    BaseAdapterDelegate<ContinueCheckout, BaseViewHolder<VhBasketContinueCheckoutBinding>>(ContinueCheckout::class.java){

    override fun bindViewHolder(item: ContinueCheckout, holder: BaseViewHolder<VhBasketContinueCheckoutBinding>) {
        holder.bind {
            btnContinueCheckout.setOnClickListener { onContinueCheckoutClicked() }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhBasketContinueCheckoutBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_basket_continue_checkout,
                parent,
                false
            )
        )
}

object ContinueCheckout