package com.marekpdev.shoppingapp.ui.addresses

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.Order
import com.marekpdev.shoppingapp.models.PaymentCard

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */

class AddressVH (view: View): RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_address
    }

    private var tvAddressLine1: TextView = view.findViewById(R.id.tvAddressLine1)
    private var tvAddressLine2: TextView = view.findViewById(R.id.tvAddressLine2)
    private var tvPostcode: TextView = view.findViewById(R.id.tvPostcode)
    private var tvCity: TextView = view.findViewById(R.id.tvCity)
    private var tvCountry: TextView = view.findViewById(R.id.tvCountry)

    fun bind(address: Address) {
        tvAddressLine1.text = address.line1
        tvAddressLine2.text = address.line2
        tvPostcode.text = address.postcode
        tvCity.text = address.city
        tvCountry.text = address.country
    }

}