package com.marekpdev.shoppingapp.ui.home.products

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhHomeBannerBinding
import com.marekpdev.shoppingapp.databinding.VhHomeProductsBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.marekpdev.shoppingapp.ui.home.banner.adapter.HomeBannerAdapter

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class HomeProductsAdapterDelegate(private val onHomeProductClicked: (Product) -> Unit) :
    BaseAdapterDelegate<HomeProducts, BaseViewHolder<VhHomeProductsBinding>>(HomeProducts::class.java){

    private val onShowMoreClicked: (String) -> Unit = {
        Log.d("FEO33", "Clicked showMore")
    }

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked product")
        onHomeProductClicked(it)
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(ProductHeightConstAdapterDelegate(onProductClicked))
            .addDelegate(HomeShowMoreAdapterDelegate(onShowMoreClicked))
    )

    override fun bindViewHolder(item: HomeProducts, holder: BaseViewHolder<VhHomeProductsBinding>) {
        holder.bind {

            rvHomeProducts.layoutManager = LinearLayoutManager(holder.context, LinearLayoutManager.HORIZONTAL, false)
            rvHomeProducts.adapter = adapter

            val items = mutableListOf<Any>().apply {
                item.products.forEach { add(it) }
                add("Showmore")
            }
            adapter.replaceData(items)
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhHomeProductsBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_home_products,
                parent,
                false
            )
        )
}