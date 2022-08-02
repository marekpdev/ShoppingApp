package com.marekpdev.shoppingapp.ui.checkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhCheckoutSelectDeliveryAddressBinding
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class CheckoutSelectDeliveryAddressDelegate(private val onSelectDeliveryAddress: () -> Unit) :
    BaseAdapterDelegate<SelectDeliveryAddress, BaseViewHolder<VhCheckoutSelectDeliveryAddressBinding>>(SelectDeliveryAddress::class.java){

    override fun bindViewHolder(item: SelectDeliveryAddress, holder: BaseViewHolder<VhCheckoutSelectDeliveryAddressBinding>) {
        holder.bind {
            root.setOnClickListener { onSelectDeliveryAddress() }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhCheckoutSelectDeliveryAddressBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_checkout_select_delivery_address,
                parent,
                false
            )
        )
}

object SelectDeliveryAddress