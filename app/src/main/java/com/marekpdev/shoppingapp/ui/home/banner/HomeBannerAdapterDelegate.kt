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
import com.marekpdev.shoppingapp.ui.home.banner.old.HomeBannerAdapter

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class HomeBannerAdapterDelegate(private val onBannerClicked: () -> Unit) :
    BaseAdapterDelegate<HomeBannerImages, BaseViewHolder<VhHomeBannerBinding>>(HomeBannerImages::class.java){

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(BannerImageAdapterDelegate())
    )

    override fun bindViewHolder(item: HomeBannerImages, holder: BaseViewHolder<VhHomeBannerBinding>) {
        holder.bind {
            root.setOnClickListener { onBannerClicked() }

            // NEW WORKFLOW
//            vpHomeBanner.adapter = adapter
//            adapter.replaceData(item.images)
            // OLD WORKFLOW
            vpHomeBanner.adapter = HomeBannerAdapter(
                listOf(
                    R.drawable.home_banner_1,
                    R.drawable.home_banner_2,
                    R.drawable.home_banner_3
                )
            )

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