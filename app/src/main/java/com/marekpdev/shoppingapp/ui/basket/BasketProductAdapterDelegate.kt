package com.marekpdev.shoppingapp.ui.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhBasketProductBinding
import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.squareup.picasso.Picasso

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class BasketProductAdapterDelegate(private val onBasketProductClicked: (BasketProduct) -> Unit) :
    BaseAdapterDelegate<BasketProduct, BaseViewHolder<VhBasketProductBinding>>(BasketProduct::class.java){

    override fun bindViewHolder(item: BasketProduct, holder: BaseViewHolder<VhBasketProductBinding>) {
        holder.bind {
            root.setOnClickListener { onBasketProductClicked(item) }

            tvProductName.text = item.name
            tvProductPrice.text = item.currency + "" + item.price

            Picasso.get().load(item.images.first()).into(ivProductImage)

//            spProductSize.apply {
//                adapter = ArrayAdapter(
//                    holder.context,
//                    R.layout.support_simple_spinner_dropdown_item,
//                    item.availableSizes.map { it.name }
//                )
//                onItemSelectedListener = SpinnerItemListener(item.availableSizes) { size ->
//
//                }
//            }
//
//            spProductColor.apply {
//                adapter = ArrayAdapter(
//                    holder.context,
//                    R.layout.support_simple_spinner_dropdown_item,
//                    item.availableColors.map { it.name }
//                )
//                onItemSelectedListener = SpinnerItemListener(item.availableColors) { color ->
//
//                }
//            }

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