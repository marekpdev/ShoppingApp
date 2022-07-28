package com.marekpdev.shoppingapp.ui.addresses.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhAddAddressBinding
import com.marekpdev.shoppingapp.databinding.VhAddressBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class AddAddressAdapterDelegate(private val onAddAddressClicked: () -> Unit) :
    BaseAdapterDelegate<AddAddress, BaseViewHolder<VhAddAddressBinding>>(AddAddress::class.java){

    override fun bindViewHolder(item: AddAddress, holder: BaseViewHolder<VhAddAddressBinding>) {
        holder.bind {
            root.setOnClickListener { onAddAddressClicked() }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhAddAddressBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_add_address,
                parent,
                false
            )
        )
}

object AddAddress