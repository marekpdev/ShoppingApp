package com.marekpdev.shoppingapp.ui.checkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhCheckoutDeliveryAddressBinding
import com.marekpdev.shoppingapp.databinding.VhCheckoutSelectPaymentMethodBinding
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class CheckoutSelectPaymentMethodDelegate(private val onSelectPaymentMethod: () -> Unit) :
    BaseAdapterDelegate<SelectPaymentMethod, BaseViewHolder<VhCheckoutSelectPaymentMethodBinding>>(SelectPaymentMethod::class.java){

    override fun bindViewHolder(item: SelectPaymentMethod, holder: BaseViewHolder<VhCheckoutSelectPaymentMethodBinding>) {
        holder.bind {
            root.setOnClickListener { onSelectPaymentMethod() }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhCheckoutSelectPaymentMethodBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_checkout_select_payment_method,
                parent,
                false
            )
        )
}

object SelectPaymentMethod