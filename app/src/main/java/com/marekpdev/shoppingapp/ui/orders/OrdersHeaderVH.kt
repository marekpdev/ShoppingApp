package com.marekpdev.shoppingapp.ui.orders

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */

class OrdersHeaderVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_orders_header
    }

    private var tvOrderHeader: TextView = view.findViewById(R.id.tvOrderHeader)

    fun bind(header: String) {
        Log.d("FEO33", "OrdersHeaderVH")
        tvOrderHeader.text = header
    }

}