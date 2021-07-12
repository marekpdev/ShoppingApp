package com.marekpdev.shoppingapp.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.repository.ProductsRepository
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
class ProductViewModel @Inject constructor(private val productId: Long, private val productsRepository: ProductsRepository): ViewModel() {

    private val _product: MutableLiveData<Product> by lazy {
        MutableLiveData<Product>().also {
            loadProduct()
        }
    }
    val product: LiveData<Product> = _product

    // TODO need to add that whenever product is changed the selected size and color is reset?
    // TODO what about handling empty value? should i use null? or is there some better way like LiveData.EMPTY or something similar?
    private val _selectedSize = MutableLiveData<Size?>(null)
    val selectedSize: LiveData<Size?> = _selectedSize

    private val _selectedColor = MutableLiveData<Color?>(null)
    val selectedColor: LiveData<Color?> = _selectedColor

    private val _productAddedEvent = LiveEvent<Any>()
    val productAddedEvent: LiveData<Any> = _productAddedEvent

    init {

    }

    private fun loadProduct() {

    }

    fun selectSize(size: Size){
        _selectedSize.value = size
        Log.d("FEO33", "Clicked ${size.name}")
    }

    fun selectColor(color: Color){
        _selectedColor.value = color
        Log.d("FEO33", "Clicked ${color.name}")
    }

    fun addProduct(){
        _productAddedEvent.value = Any()
    }
}