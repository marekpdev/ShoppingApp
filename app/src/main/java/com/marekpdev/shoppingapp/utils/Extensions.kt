package com.marekpdev.shoppingapp.utils

import android.widget.EditText

/**
 * Created by Marek Pszczolka on 05/06/2022.
 */
fun EditText.setTextIfDifferent(text: String){
    if(getText().toString() != text){
        setText(text)
    }
}