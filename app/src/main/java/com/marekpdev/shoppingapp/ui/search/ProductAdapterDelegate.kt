package com.marekpdev.shoppingapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhAddressBinding
import com.marekpdev.shoppingapp.databinding.VhOrderBinding
import com.marekpdev.shoppingapp.databinding.VhOrdersHeaderBinding
import com.marekpdev.shoppingapp.databinding.VhProductBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.Order
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class ProductAdapterDelegate(private val onProductClicked: (Product) -> Unit) :
    BaseAdapterDelegate<Product, BaseViewHolder<VhProductBinding>>(Product::class.java){

    override fun bindViewHolder(item: Product, holder: BaseViewHolder<VhProductBinding>) {
        holder.bind {
            root.setOnClickListener { onProductClicked(item) }

            tvProductName.text = item.name
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhProductBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_product,
                parent,
                false
            )
        )
}