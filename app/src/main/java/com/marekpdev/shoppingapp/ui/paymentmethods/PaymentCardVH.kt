package com.marekpdev.shoppingapp.ui.paymentmethods

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Order
import com.marekpdev.shoppingapp.models.PaymentCard

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */

class PaymentCardVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_payment_card
    }

    private var tvCardProvider: TextView = view.findViewById(R.id.tvCardProvider)
    private var tvCardNumber: TextView = view.findViewById(R.id.tvCardNumber)

    fun bind(paymentCard: PaymentCard) {
        tvCardProvider.setText("VISA")
        tvCardNumber.setText("**** **** **** 4343")
    }

}