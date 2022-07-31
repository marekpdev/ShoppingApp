package com.marekpdev.shoppingapp.ui.paymentmethods.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhPaymentMethodBinding
import com.marekpdev.shoppingapp.models.payments.PaymentMethod
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class PaymentMethodAdapterDelegate(private val onPaymentMethodClicked: (PaymentMethod) -> Unit) :
    BaseAdapterDelegate<PaymentMethod, BaseViewHolder<VhPaymentMethodBinding>>(PaymentMethod::class.java){

    override fun bindViewHolder(item: PaymentMethod, holder: BaseViewHolder<VhPaymentMethodBinding>) {
        holder.bind {
            root.setOnClickListener { onPaymentMethodClicked(item) }

            tvLabel.text = item.label
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhPaymentMethodBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_payment_method,
                parent,
                false
            )
        )
}