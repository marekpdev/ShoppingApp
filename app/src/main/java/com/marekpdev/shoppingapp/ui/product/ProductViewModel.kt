package com.marekpdev.shoppingapp.ui.product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hadilq.liveevent.LiveEvent
import com.marekpdev.shoppingapp.extensions.asLiveData
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.repository.products.ProductsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 11/07/2021.
 */

//https://medium.com/scalereal/providing-assistedinject-supported-viewmodel-for-composable-using-hilt-ae973632e29a
//https://www.geeksforgeeks.org/assisted-dependency-injection-in-viewmodel-with-dagger-hilt-in-android/

//@HiltViewModel
// more info why we needed to remove @HiltViewModel to make AssistedInject work:
// https://stackoverflow.com/questions/68649447/viewmodel-constructor-should-be-annotated-with-inject-instead-of-assistedinjec
class ProductViewModel @AssistedInject constructor(
    // todo need to use this variable in the constructor with 'assisted injection'? rather than
    // setting it via setter
    // need to use this with dagger
    private val productsRepository: ProductsRepository,
    @Assisted private val productId: Long,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(productId: Long): ProductViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {

        fun provideFactory(
            assistedFactory: Factory,
            productId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(productId) as T
            }
        }
    }

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
        Log.d("FEO56", "Product id = $productId, and from repo " + productsRepository.getProduct(1).name)
    }

    fun foo() {

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