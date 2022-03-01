package com.marekpdev.shoppingapp.ui.home.banner.items.old

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeBannerAdapter(private var images: List<Int> = listOf()) : RecyclerView.Adapter<HomeBannerAdapterImageVH>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): HomeBannerAdapterImageVH {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(HomeBannerAdapterImageVH.layout, viewGroup, false)

        return HomeBannerAdapterImageVH(view)
    }

    override fun onBindViewHolder(viewHolder: HomeBannerAdapterImageVH, position: Int) {
        viewHolder.bind(images[position])
    }

    override fun getItemCount() = images.size

    fun replaceData(images: List<Int>) {
        this.images = images
        notifyDataSetChanged()
    }
}