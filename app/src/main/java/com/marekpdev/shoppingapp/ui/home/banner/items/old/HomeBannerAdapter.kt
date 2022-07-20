package com.marekpdev.shoppingapp.ui.home.banner.items.old

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.models.HomeBanner

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeBannerAdapter(private var homeBanners: List<HomeBanner> = listOf()) : RecyclerView.Adapter<HomeBannerAdapterImageVH>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeBannerAdapterImageVH {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(HomeBannerAdapterImageVH.layout, viewGroup, false)

        return HomeBannerAdapterImageVH(view)
    }

    override fun onBindViewHolder(viewHolder: HomeBannerAdapterImageVH, position: Int) {
        viewHolder.bind(homeBanners[position])
    }

    override fun getItemCount() = homeBanners.size

    fun replaceData(homeBanners: List<HomeBanner>) {
        this.homeBanners = homeBanners
        notifyDataSetChanged()
    }
}