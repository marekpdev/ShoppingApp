package com.marekpdev.shoppingapp.ui.product.images

import android.view.View
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.squareup.picasso.Picasso

/**
 * Created by Marek Pszczolka on 25/04/2021.
 */
class ImageVH(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_product_image

    }

    val ivProductImage: ImageView

    init {
        // Define click listener for the ViewHolder's View.
        ivProductImage = view.findViewById(R.id.ivProductImage)
    }

    fun bind(imagePath: String) {
        Picasso.get().load(imagePath).into(ivProductImage)
    }
}