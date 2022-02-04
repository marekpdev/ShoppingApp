package com.marekpdev.shoppingapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.models.Product

/**
 * Created by Marek Pszczolka on 10/07/2021.
 */
class ProductsRVAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val items = mutableListOf<Product>()

    fun setData(items: List<Product>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductVH(
            view = layoutInflater.inflate(ProductVH.layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductVH).bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}