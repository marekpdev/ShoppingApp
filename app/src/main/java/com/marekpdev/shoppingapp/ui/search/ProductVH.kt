package com.marekpdev.shoppingapp.ui.search

import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 10/07/2021.
 */
class ProductVH (view: View): RecyclerView.ViewHolder(view){

    private var tvProductName: TextView = view.findViewById(R.id.tvProductName)

    companion object{
        @LayoutRes
        val layout = R.layout.vh_product
    }

    fun bind(product: Product) {
        tvProductName.text = product.name
    }
}