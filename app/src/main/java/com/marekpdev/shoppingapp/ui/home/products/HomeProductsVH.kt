package com.marekpdev.shoppingapp.ui.home.products

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.ui.favourite.ProductGridRVAdapter
import com.marekpdev.shoppingapp.ui.home.products.adapter.HomeProductsRVAdapter

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeProductsVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_home_products
    }

    private var rvHomeProducts: RecyclerView = view.findViewById(R.id.rvHomeProducts)

    fun bind(products: List<Product>) {
        rvHomeProducts.adapter = HomeProductsRVAdapter(products)
        rvHomeProducts.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
    }

}