package com.marekpdev.shoppingapp.ui.productvh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhProductBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class ProductAdapterDelegate(private val onProductClicked: (Product) -> Unit,
                             private val onToggleFavourite: (Product) -> Unit) :
    BaseAdapterDelegate<Product, BaseViewHolder<VhProductBinding>>(Product::class.java){

    override fun bindViewHolder(item: Product, holder: BaseViewHolder<VhProductBinding>) {
        holder.bind {
            root.setOnClickListener { onProductClicked(item) }

            tvProductName.text = item.name

            // todo use onToggleFavourite
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