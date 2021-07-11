package com.marekpdev.shoppingapp.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
class ProductViewModel: ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    private val _selectedSize = MutableLiveData<Size>()
    val selectedSize: LiveData<Size>
        get() = _selectedSize

    private val _selectedColor = MutableLiveData<Color>()
    val selectedColor: LiveData<Color>
        get() = _selectedColor


    init {

    }
}