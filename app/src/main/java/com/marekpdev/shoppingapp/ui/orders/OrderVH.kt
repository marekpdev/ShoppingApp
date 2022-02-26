package com.marekpdev.shoppingapp.ui.orders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Order

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */

class OrderVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_order
    }

    private var tvOrderId: TextView = view.findViewById(R.id.tvOrderId)
    private var tvOrderStatus: TextView = view.findViewById(R.id.tvOrderStatus)
    private var btnViewDetails: TextView = view.findViewById(R.id.btnViewDetails)

    fun bind(order: Order) {

    }

}