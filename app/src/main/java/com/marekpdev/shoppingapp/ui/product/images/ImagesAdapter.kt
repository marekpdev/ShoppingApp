package com.marekpdev.shoppingapp.ui.product.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R

/**
 * Created by Marek Pszczolka on 25/04/2021.
 */

class ImagesAdapter(private val images: List<Int>) : RecyclerView.Adapter<ImageVH>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ImageVH {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.vh_product_image, viewGroup, false)

        return ImageVH(view)
    }

    override fun onBindViewHolder(viewHolder: ImageVH, position: Int) {
        viewHolder.bind(images[position])
    }

    override fun getItemCount() = images.size
}