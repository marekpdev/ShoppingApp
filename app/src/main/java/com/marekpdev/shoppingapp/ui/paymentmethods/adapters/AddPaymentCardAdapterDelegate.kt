package com.marekpdev.shoppingapp.ui.paymentmethods.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhAddAddressBinding
import com.marekpdev.shoppingapp.databinding.VhAddPaymentCardBinding
import com.marekpdev.shoppingapp.databinding.VhAddressBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class AddPaymentCardAdapterDelegate(private val onAddPaymentCardClicked: () -> Unit) :
    BaseAdapterDelegate<AddPaymentCard, BaseViewHolder<VhAddPaymentCardBinding>>(AddPaymentCard::class.java){

    override fun bindViewHolder(item: AddPaymentCard, holder: BaseViewHolder<VhAddPaymentCardBinding>) {
        holder.bind {
            root.setOnClickListener { onAddPaymentCardClicked() }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhAddPaymentCardBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_add_payment_card,
                parent,
                false
            )
        )
}

object AddPaymentCard