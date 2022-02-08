package com.marekpdev.shoppingapp.utils

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R

/**
 * Created by Marek Pszczolka on 08/02/2022.
 */
class EmptyVH(view: View) : RecyclerView.ViewHolder(view) {

    companion object {
        @LayoutRes
        val layout = R.layout.vh_empty
    }

}