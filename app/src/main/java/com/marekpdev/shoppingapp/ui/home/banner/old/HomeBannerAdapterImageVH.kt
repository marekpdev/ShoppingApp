package com.marekpdev.shoppingapp.ui.home.banner.old

import android.view.View
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeBannerAdapterImageVH(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_banner_image_old

    }

    val ivBannerImage: ImageView

    init {
        // Define click listener for the ViewHolder's View.
        ivBannerImage = view.findViewById(R.id.ivBannerImage)
    }

    fun bind(imageRes: Int) {
        ivBannerImage.setImageResource(imageRes)
    }
}