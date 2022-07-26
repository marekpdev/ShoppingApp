package com.marekpdev.shoppingapp.ui.basket.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhBasketProductBinding
import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.marekpdev.shoppingapp.ui.utils.SpinnerItemListener
import com.squareup.picasso.Picasso

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class BasketProductAdapterDelegate(private val onBasketProductClicked: (BasketProduct) -> Unit,
                                   private val onRemoveBasketProductClicked: (BasketProduct) -> Unit,
                                   private val onUpdateSize: (BasketProduct, Size) -> Unit,
                                   private val onUpdateColor: (BasketProduct, Color) -> Unit
) :
    BaseAdapterDelegate<BasketProduct, BaseViewHolder<VhBasketProductBinding>>(BasketProduct::class.java){

    override fun bindViewHolder(item: BasketProduct, holder: BaseViewHolder<VhBasketProductBinding>) {
        holder.bind {
            root.setOnClickListener { onBasketProductClicked(item) }

            tvProductName.text = item.name
            tvProductPrice.text = item.currency + "" + item.price

            Picasso.get().load(item.images.first()).into(ivProductImage)

            ivRemove.setOnClickListener { onRemoveBasketProductClicked(item) }

            spProductSize.apply {
                adapter = ArrayAdapter(
                    holder.context,
                    R.layout.support_simple_spinner_dropdown_item,
                    item.availableSizes.map { it.name }
                )

                // TODO fix (issue both with size and color selector)
                // every time the size/color is selected
                // the SpinnerItemListener is called multiple times
                // so it deselects previously selected item
                onItemSelectedListener = SpinnerItemListener(item.availableSizes) { size ->
                    Log.d("FEO33", "Size selected for ${item.id}")
                    onUpdateSize(item, size)
                }
            }

            spProductColor.apply {
                adapter = ArrayAdapter(
                    holder.context,
                    R.layout.support_simple_spinner_dropdown_item,
                    item.availableColors.map { it.name }
                )

                onItemSelectedListener = SpinnerItemListener(item.availableColors) { color ->
                    Log.d("FEO33", "color selected for ${item.id}")
                    onUpdateColor(item, color)
                }
            }

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