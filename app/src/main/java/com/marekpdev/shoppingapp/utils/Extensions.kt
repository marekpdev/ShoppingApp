package com.marekpdev.shoppingapp.utils

import android.widget.TextView

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
fun TextView.setTextIfDifferent(text: String){
    if(getText().toString() != text){
        setText(text)
    }
}