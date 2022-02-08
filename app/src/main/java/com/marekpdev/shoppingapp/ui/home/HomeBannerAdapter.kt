package com.marekpdev.shoppingapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeBannerAdapter(private val images: List<Int>) : RecyclerView.Adapter<BannerImageVH>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BannerImageVH {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.vh_banner_image, viewGroup, false)

        return BannerImageVH(view)
    }

    override fun onBindViewHolder(viewHolder: BannerImageVH, position: Int) {
        viewHolder.bind(images[position])
    }

    override fun getItemCount() = images.size
}