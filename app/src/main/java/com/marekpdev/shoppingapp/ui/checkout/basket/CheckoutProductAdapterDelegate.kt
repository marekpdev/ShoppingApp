package com.marekpdev.shoppingapp.ui.checkout.basket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhCheckoutProductBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
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

            val sizesSpinnerAdapter = ArrayAdapter(holder.context, R.layout.support_simple_spinner_dropdown_item, item.availableSizes.map { it.name })
            spProductSize.adapter = sizesSpinnerAdapter

            spProductSize.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val color = item.availableSizes[position]
                    Log.d("FEO33", "Selected $color")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("FEO33", "onNothingSelected")
                }
            }

            val colorsSpinnerAdapter = ArrayAdapter(holder.context, R.layout.support_simple_spinner_dropdown_item, item.availableColors.map { it.name })
            spProductColor.adapter = colorsSpinnerAdapter

            spProductColor.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val size = item.availableSizes[position]
                    Log.d("FEO33", "Selected $size")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("FEO33", "onNothingSelected")
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