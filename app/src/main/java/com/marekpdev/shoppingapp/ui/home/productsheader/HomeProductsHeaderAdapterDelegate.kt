package com.marekpdev.shoppingapp.ui.home.productsheader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhHomeBannerBinding
import com.marekpdev.shoppingapp.databinding.VhHomeProductsHeaderBinding
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.marekpdev.shoppingapp.ui.home.banner.adapter.HomeBannerAdapter

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class HomeProductsHeaderAdapterDelegate(private val onHomeProductsHeaderClicked: (HomeProductsHeader) -> Unit) :
    BaseAdapterDelegate<HomeProductsHeader, BaseViewHolder<VhHomeProductsHeaderBinding>>(HomeProductsHeader::class.java){

    override fun bindViewHolder(item: HomeProductsHeader, holder: BaseViewHolder<VhHomeProductsHeaderBinding>) {
        holder.bind {
            root.setOnClickListener { onHomeProductsHeaderClicked(item) }

            tvHeaderLabel.text = item.text
            tvHeaderShowMore.text = "Show more"
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