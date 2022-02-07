package com.marekpdev.shoppingapp.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 25/04/2021.
 */

class ProductGridRVAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductGridVH>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductGridVH {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(ProductGridVH.layout, viewGroup, false)

        return ProductGridVH(view)
    }

    override fun onBindViewHolder(viewHolder: ProductGridVH, position: Int) {
        viewHolder.bind(products[position])
    }

    override fun getItemCount() = products.size
}