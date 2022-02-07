package com.marekpdev.shoppingapp.ui.favourite

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 07/02/2022.
 */
class ProductGridVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.product_grid_vh
    }

    private var tvProductName: TextView = view.findViewById(R.id.tvProductName)
    private var ivProductImage: ImageView = view.findViewById(R.id.ivProductImage)
    private var ivFavouriteToggle: ImageView = view.findViewById(R.id.ivFavouriteToggle)

    fun bind(product: Product) {
        tvProductName.text = product.name

        ivProductImage.setImageResource(
            when(product.id.toInt() % 4){
                1 -> R.drawable.product1
                2 -> R.drawable.product2
                3 ->  R.drawable.product3
                else -> R.drawable.product4
            }
        )

        ivProductImage.clipToOutline = true
    }

}