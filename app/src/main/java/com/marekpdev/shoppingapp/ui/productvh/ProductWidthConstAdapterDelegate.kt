package com.marekpdev.shoppingapp.ui.productvh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhProductGridWidthConstraintBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.squareup.picasso.Picasso

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class ProductWidthConstAdapterDelegate(private val onProductClicked: (Product) -> Unit,
                                       private val onToggleFavourite: (Product) -> Unit) :
    BaseAdapterDelegate<Product, BaseViewHolder<VhProductGridWidthConstraintBinding>>(Product::class.java){

    override fun bindViewHolder(item: Product, holder: BaseViewHolder<VhProductGridWidthConstraintBinding>) {
        holder.bind {

            root.setOnClickListener { onProductClicked(item) }

            productLayout.apply {
                tvProductName.text = item.name
                tvProductPrice.text = item.currency + "" + item.price

                Picasso.get().load(item.images.first()).into(ivProductImage)

                ivProductImage.clipToOutline = true

                ivFavouriteToggle.setOnClickListener { onToggleFavourite(item) }
                ivFavouriteToggle.clipToOutline = true

                val favouriteIcon = when(item.isFavoured){
                    true -> R.drawable.ic_favourite_1_red
                    else -> R.drawable.ic_favourite_1_black
                }
                ivFavouriteToggle.setImageResource(favouriteIcon)
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhProductGridWidthConstraintBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_product_grid_width_constraint,
                parent,
                false
            )
        )
}