package com.marekpdev.shoppingapp.ui.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marekpdev.shoppingapp.ui.base.BaseViewModel
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
    store: ProductStore,
    @Assisted private val productId: Long,
) : BaseViewModel<ProductState, ProductAction, ProductCommand>(store) {

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

    init {
        Log.d("FEO33", "INIT ProductViewModel")
        dispatch(ProductAction.FetchProduct(productId))
    }
}