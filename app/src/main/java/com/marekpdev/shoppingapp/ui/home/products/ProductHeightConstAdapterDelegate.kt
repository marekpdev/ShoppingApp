package com.marekpdev.shoppingapp.ui.home.products

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhProductGridHeightConstraintBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class ProductHeightConstAdapterDelegate(private val onProductClicked: (Product) -> Unit) :
    BaseAdapterDelegate<Product, BaseViewHolder<VhProductGridHeightConstraintBinding>>(Product::class.java){

    override fun bindViewHolder(item: Product, holder: BaseViewHolder<VhProductGridHeightConstraintBinding>) {
        holder.bind {

            root.setOnClickListener { onProductClicked(item) }

            productLayout.apply {
                tvProductName.text = item.name

                ivProductImage.setImageResource(
                    when(item.id.toInt() % 4){
                        1 -> R.drawable.product1
                        2 -> R.drawable.product2
                        3 ->  R.drawable.product3
                        else -> R.drawable.product4
                    }
                )

                ivProductImage.clipToOutline = true

                ivFavouriteToggle.setOnClickListener { Log.d("FEO33", "Log") }
                ivFavouriteToggle.clipToOutline = true
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhProductGridHeightConstraintBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_product_grid_height_constraint,
                parent,
                false
            )
        )
}