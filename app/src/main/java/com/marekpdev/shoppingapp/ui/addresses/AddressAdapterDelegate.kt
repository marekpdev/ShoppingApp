package com.marekpdev.shoppingapp.ui.addresses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhAddressBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class AddressAdapterDelegate(private val onAddressClicked: (Address) -> Unit) :
    BaseAdapterDelegate<Address, BaseViewHolder<VhAddressBinding>>(Address::class.java){

    override fun bindViewHolder(item: Address, holder: BaseViewHolder<VhAddressBinding>) {
        holder.bind {
            root.setOnClickListener { onAddressClicked(item) }

            tvAddressLine1.text = item.line1
            tvAddressLine2.text = item.line2
            tvPostcode.text = item.postcode
            tvCity.text = item.city
            tvCountry.text = item.country
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhAddressBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_address,
                parent,
                false
            )
        )
}