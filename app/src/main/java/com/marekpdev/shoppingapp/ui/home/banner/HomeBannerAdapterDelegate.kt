package com.marekpdev.shoppingapp.ui.home.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhHomeBannerBinding
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.marekpdev.shoppingapp.ui.home.banner.items.BannerImageAdapterDelegate

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class HomeBannerAdapterDelegate(private val onBannerClicked: () -> Unit) :
    BaseAdapterDelegate<HomeBanners, BaseViewHolder<VhHomeBannerBinding>>(HomeBanners::class.java){

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(BannerImageAdapterDelegate())
    )

    override fun bindViewHolder(item: HomeBanners, holder: BaseViewHolder<VhHomeBannerBinding>) {
        holder.bind {
            root.setOnClickListener { onBannerClicked() }

            vpHomeBanner.adapter = adapter
            adapter.replaceData(item.homeBanners)

            TabLayoutMediator(tlHomeBanner, vpHomeBanner) { tab, position ->}.attach()
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhHomeBannerBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_home_banner,
                parent,
                false
            )
        )
}