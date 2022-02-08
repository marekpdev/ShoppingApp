package com.marekpdev.shoppingapp.ui.home.productsheader

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */

class HomeProductsHeaderVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_home_products_header
    }

    private var tvHeaderLabel: TextView = view.findViewById(R.id.tvHeaderLabel)
    private var tvHeaderShowMore: TextView = view.findViewById(R.id.tvHeaderShowMore)

    fun bind(header: String) {
        tvHeaderLabel.text = header
    }

}