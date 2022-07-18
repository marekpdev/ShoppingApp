package com.marekpdev.shoppingapp.ui.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marekpdev.shoppingapp.mvi.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

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
    productStore: ProductStore,
    @Assisted private val productId: Long,
) : BaseViewModel<ProductState, ProductAction, ProductCommand>(productStore) {

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

    // FROM times when i had LiveData here
    // TODO need to add that whenever product is changed the selected size and color is reset?
    // TODO what about handling empty value? should i use null? or is there some better way like LiveData.EMPTY or something similar?
    //
    //private val _selectedSize = MutableLiveData<Size?>(null)
    //val selectedSize = _selectedSize.asLiveData()

    init {
        Log.d("FEO920", "Sending")
        dispatch(ProductAction.FetchProduct(productId))
    }
}