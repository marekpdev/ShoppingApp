package com.marekpdev.shoppingapp.ui.utils

import android.view.View
import android.widget.AdapterView

/**
 * Created by Marek Pszczolka on 03/03/2022.
 */
class SpinnerItemListener<T>(private val items: List<T>,
                             private val onItemSelected: (T) -> Unit): AdapterView.OnItemSelectedListener {

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onItemSelected(items[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}