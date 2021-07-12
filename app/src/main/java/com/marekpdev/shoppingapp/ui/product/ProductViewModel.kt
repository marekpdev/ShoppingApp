package com.marekpdev.shoppingapp.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.repository.ProductsRepository
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
class ProductViewModel @Inject constructor(private val productsRepository: ProductsRepository): ViewModel() {

    private val _product by lazy { MutableLiveData<Product>().also { loadProduct() } }
    val product: LiveData<Product>
        get() = _product

    // TODO need to add that whenever product is changed the selected size and color is reset?
    // TODO what about handling empty value? should i use null? or is there some better way like LiveData.EMPTY or something similar?
    private val _selectedSize = MutableLiveData<Size>()
    val selectedSize: LiveData<Size>
        get() = _selectedSize

    private val _selectedColor = MutableLiveData<Color>()
    val selectedColor: LiveData<Color>
        get() = _selectedColor


    init {

    }

    private fun loadProduct() {

    }

    fun onSelectSize(size: Size){
        _selectedSize.value = size
        Log.d("FEO33", "Clicked ${size.name}")
    }

    fun onSelectColor(color: Color){
        _selectedColor.value = color
        Log.d("FEO33", "Clicked ${color.name}")
    }

    fun onAddProduct(){

    }
}