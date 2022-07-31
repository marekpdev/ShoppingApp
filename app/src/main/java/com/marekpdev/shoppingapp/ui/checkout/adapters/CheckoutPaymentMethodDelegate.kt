package com.marekpdev.shoppingapp.ui.checkout.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhCheckoutPaymentMethodBinding
import com.marekpdev.shoppingapp.models.payments.PaymentMethod
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class CheckoutPaymentMethodDelegate(private val onEditPaymentMethod: (PaymentMethod) -> Unit) :
    BaseAdapterDelegate<PaymentMethod, BaseViewHolder<VhCheckoutPaymentMethodBinding>>(PaymentMethod::class.java){

    override fun bindViewHolder(item: PaymentMethod, holder: BaseViewHolder<VhCheckoutPaymentMethodBinding>) {
        holder.bind {
            ivEdit.setOnClickListener { onEditPaymentMethod(item) }

            tvPaymentMethodLabel.text = "Payment Method"
            tvPaymentMethod.text = item.label
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhCheckoutPaymentMethodBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_checkout_payment_method,
                parent,
                false
            )
        )
}