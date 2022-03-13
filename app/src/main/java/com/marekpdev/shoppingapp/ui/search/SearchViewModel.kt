package com.marekpdev.shoppingapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */
class SearchViewModel: ViewModel() {

    private val _items = MutableLiveData<List<Product>>()
    val items: LiveData<List<Product>>
        get() = _items

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String>
        get() = _searchQuery

    private val _searchLoading = MutableLiveData<Boolean>()
    val searchLoading: LiveData<Boolean>
        get() = _searchLoading

    private val _summaryText = Transformations.map(_items) {
        "Showing ${it.size} items"
    }
    val summaryText: LiveData<String>
        get() = _summaryText

    private val _goToProductDetailsEvent = MutableLiveData<Product?>()
    val goToProductDetailsEvent: LiveData<Product?>
        get() = _goToProductDetailsEvent

    init {
        _items.value = Data.getMenu().second!!
        _searchQuery.value = ""
        _searchLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onProductClicked(product: Product){
        _goToProductDetailsEvent.value = product
    }

    fun goToProductDetailsEventFinished(){
        _goToProductDetailsEvent.value = null
    }

    fun onNewSearch(text: String){
        _searchQuery.value = text
    }
}