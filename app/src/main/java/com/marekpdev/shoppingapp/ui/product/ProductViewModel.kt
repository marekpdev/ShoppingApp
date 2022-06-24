package com.marekpdev.shoppingapp.ui.product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.marekpdev.shoppingapp.extensions.asLiveData
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    // todo need to use this variable in the constructor with 'assisted injection'? rather than
    // setting it via setter
    //private val productId: Long,
    // need to use this with dagger
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _product: MutableLiveData<Product> by lazy {
        MutableLiveData<Product>().also {
            loadProduct()
        }
    }
    val product = _product.asLiveData()

    // TODO need to add that whenever product is changed the selected size and color is reset?
    // TODO what about handling empty value? should i use null? or is there some better way like LiveData.EMPTY or something similar?
    private val _selectedSize = MutableLiveData<Size?>(null)
    val selectedSize = _selectedSize.asLiveData()

    private val _selectedColor = MutableLiveData<Color?>(null)
    val selectedColor = _selectedColor.asLiveData()

    private val _productAddedEvent = LiveEvent<Any>()
    val productAddedEvent = _productAddedEvent.asLiveData()

    init {
        Log.d("FEO33", "Product id")
    }

    private fun loadProduct() {
       // _product.value = Data.getMenu().second.find { it.id == productId }
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