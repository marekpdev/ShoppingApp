package com.marekpdev.shoppingapp.ui.home.products.adapter

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl
import com.marekpdev.shoppingapp.ui.favourite.ProductGridRVAdapter
import com.marekpdev.shoppingapp.ui.home.banner.adapter.HomeBannerAdapter

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeShowMoreVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_home_show_more
    }

    private var tvLabel: TextView = view.findViewById(R.id.tvLabel)

    fun bind() {

    }

}