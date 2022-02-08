package com.marekpdev.shoppingapp.ui.home.banner

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.ui.home.banner.adapter.HomeBannerAdapter

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeBannerVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_home_banner
    }

    private var vpHomeBanner: ViewPager2 = view.findViewById(R.id.vpHomeBanner)
    private var tlHomeBanner: TabLayout = view.findViewById(R.id.tlHomeBanner)

    fun bind() {
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