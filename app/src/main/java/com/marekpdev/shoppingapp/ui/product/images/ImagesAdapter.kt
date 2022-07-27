package com.marekpdev.shoppingapp.ui.product.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Marek Pszczolka on 25/04/2021.
 */

class ImagesAdapter : RecyclerView.Adapter<ImageVH>(){

    private val images: MutableList<String> = mutableListOf()

    fun setData(images: List<String>) {
        this.images.clear()
        this.images.addAll(images)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ImageVH {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(ImageVH.layout, viewGroup, false)

        return ImageVH(view)
    }

    override fun onBindViewHolder(viewHolder: ImageVH, position: Int) {
        viewHolder.bind(images[position])
    }

    override fun getItemCount() = images.size
}