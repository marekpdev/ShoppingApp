package com.marekpdev.shoppingapp.ui.home.productsheader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhHomeProductsHeaderBinding
import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class HomeProductsHeaderAdapterDelegate(private val onShowMoreClicked: (Category) -> Unit) :
    BaseAdapterDelegate<HomeProductsHeader, BaseViewHolder<VhHomeProductsHeaderBinding>>(HomeProductsHeader::class.java){

    override fun bindViewHolder(item: HomeProductsHeader, holder: BaseViewHolder<VhHomeProductsHeaderBinding>) {
        holder.bind {
            tvHeaderLabel.text = item.category.name
            tvHeaderShowMore.text = "Show more"

            tvHeaderShowMore.setOnClickListener { onShowMoreClicked(item.category) }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhHomeProductsHeaderBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_home_products_header,
                parent,
                false
            )
        )
}