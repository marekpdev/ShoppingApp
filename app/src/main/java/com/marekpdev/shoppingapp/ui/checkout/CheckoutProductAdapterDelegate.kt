package com.marekpdev.shoppingapp.ui.checkout

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhCheckoutProductBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.marekpdev.shoppingapp.ui.utils.SpinnerItemListener
import com.squareup.picasso.Picasso

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class CheckoutProductAdapterDelegate(private val onProductClicked: (Product) -> Unit,
                                     private val onProductLongClicked: (Product) -> Unit) :
    BaseAdapterDelegate<Product, BaseViewHolder<VhCheckoutProductBinding>>(Product::class.java){

    override fun bindViewHolder(item: Product, holder: BaseViewHolder<VhCheckoutProductBinding>) {
        holder.bind {
            root.setOnClickListener { onProductClicked(item) }
            root.setOnLongClickListener {
                onProductLongClicked(item)
                true
            }

            tvProductName.text = item.name
            tvProductPrice.text = item.currency + "" + item.price

            Picasso.get().load(item.images.first()).into(ivProductImage)

            spProductSize.apply {
                adapter = ArrayAdapter(
                    holder.context,
                    R.layout.support_simple_spinner_dropdown_item,
                    item.availableSizes.map { it.name }
                )
                onItemSelectedListener = SpinnerItemListener(item.availableSizes) { size ->
                    Log.d("FEO33", "Selected $size")
                }
            }

            spProductColor.apply {
                adapter = ArrayAdapter(
                    holder.context,
                    R.layout.support_simple_spinner_dropdown_item,
                    item.availableColors.map { it.name }
                )
                onItemSelectedListener = SpinnerItemListener(item.availableColors) { color ->
                    Log.d("FEO33", "Selected $color")
                }
            }

        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhCheckoutProductBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_checkout_product,
                parent,
                false
            )
        )
}