package com.marekpdev.shoppingapp.ui.paymentmethods

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhOrderBinding
import com.marekpdev.shoppingapp.databinding.VhOrdersHeaderBinding
import com.marekpdev.shoppingapp.databinding.VhPaymentCardBinding
import com.marekpdev.shoppingapp.models.Order
import com.marekpdev.shoppingapp.models.PaymentCard
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class PaymentCardAdapterDelegate(private val onPaymentCardClicked: (PaymentCard) -> Unit) :
    BaseAdapterDelegate<PaymentCard, BaseViewHolder<VhPaymentCardBinding>>(PaymentCard::class.java){

    override fun bindViewHolder(item: PaymentCard, holder: BaseViewHolder<VhPaymentCardBinding>) {
        holder.bind {
            root.setOnClickListener { onPaymentCardClicked(item) }

            tvCardProvider.text = item.provider
            tvCardNumber.text = item.number
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhPaymentCardBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_payment_card,
                parent,
                false
            )
        )
}