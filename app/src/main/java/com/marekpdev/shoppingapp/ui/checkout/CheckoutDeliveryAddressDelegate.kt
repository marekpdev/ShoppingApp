package com.marekpdev.shoppingapp.ui.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhCheckoutDeliveryAddressBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class CheckoutDeliveryAddressDelegate(private val onEditAddress: (Address) -> Unit) :
    BaseAdapterDelegate<Address, BaseViewHolder<VhCheckoutDeliveryAddressBinding>>(Address::class.java){

    override fun bindViewHolder(item: Address, holder: BaseViewHolder<VhCheckoutDeliveryAddressBinding>) {
        holder.bind {
            ivEdit.setOnClickListener { onEditAddress(item) }

            tvDeliveryAddressLabel.text = "Delivery Address X"
            val sbAddress = StringBuilder()
            sbAddress.appendLine(item.line1)
            sbAddress.appendLine(item.line2)
            sbAddress.appendLine(item.postcode)
            sbAddress.appendLine(item.city)
            sbAddress.appendLine(item.country)
            tvDeliveryAddress.text = sbAddress.trim().toString()
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhCheckoutDeliveryAddressBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_checkout_delivery_address,
                parent,
                false
            )
        )
}