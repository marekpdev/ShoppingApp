package com.marekpdev.shoppingapp.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
class ProductViewModelFactory(private val productId: Long): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // todo need to uncomment
        if(modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(productId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}