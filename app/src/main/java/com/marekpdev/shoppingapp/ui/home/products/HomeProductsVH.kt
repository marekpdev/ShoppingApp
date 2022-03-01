package com.marekpdev.shoppingapp.ui.home.products

import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.home.ProductHeightConstAdapterDelegate

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class HomeProductsVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_home_products
    }

    private val onShowMoreClicked: (String) -> Unit = {
        Log.d("FEO33", "Clicked showMore")
    }

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked product")
    }

    private var rvHomeProducts: RecyclerView = view.findViewById(R.id.rvHomeProducts)

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(ProductHeightConstAdapterDelegate(onProductClicked))
            .addDelegate(HomeShowMoreAdapterDelegate(onShowMoreClicked))
    )

    fun bind(products: List<Product>) {
        rvHomeProducts.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        rvHomeProducts.adapter = adapter

        val items = mutableListOf<Any>().apply {
            products.forEach { add(it) }
            add("Showmore")
        }
        adapter.replaceData(items)
    }

}