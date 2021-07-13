package com.marekpdev.shoppingapp.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Marek Pszczolka on 13/07/2021.
 */
fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>