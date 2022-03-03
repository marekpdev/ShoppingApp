package com.marekpdev.shoppingapp.ui.checkout.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhBasketProductBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.squareup.picasso.Picasso

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class CheckoutBasketProductAdapterDelegate(private val onProductClicked: (Product) -> Unit,
                                           private val onProductLongClicked: (Product) -> Unit) :
    BaseAdapterDelegate<Product, BaseViewHolder<VhBasketProductBinding>>(Product::class.java){

    override fun bindViewHolder(item: Product, holder: BaseViewHolder<VhBasketProductBinding>) {
        holder.bind {
            root.setOnClickListener { onProductClicked(item) }
            root.setOnLongClickListener {
                onProductLongClicked(item)
                true
            }

            tvProductName.text = item.name
            tvProductPrice.text = item.currency + "" + item.price

            Picasso.get().load(item.images.first()).into(ivProductImage)
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhBasketProductBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_basket_product,
                parent,
                false
            )
        )
}