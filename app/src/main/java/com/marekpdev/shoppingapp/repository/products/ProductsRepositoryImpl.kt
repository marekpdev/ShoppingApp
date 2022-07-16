package com.marekpdev.shoppingapp.repository.products

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
class ProductsRepositoryImpl @Inject constructor(
   // private val productsApi: ProductsApi,
    //private val productsDao: ProductsDao
): ProductsRepository {

    private val allProductsFlow = MutableStateFlow(Data.products.toList())

    init {
        Log.d("FEO440", "CREATING NEW ProductsRepositoryImpl")
    }

    override suspend fun getProduct(id: Long): Product? {
        delay(1000) // TODO just for testing
        val product = allProductsFlow.value.find {
            Log.d("FEO400", "This id ${it.id} - searched $id")
            it.id == id
        }

        return product
    }

    override suspend fun toggleFavourite(product: Product) {
        val indexOf = allProductsFlow.value.indexOf(product)
        if (indexOf > 0) {
            Log.d("FEO410", "Index found $indexOf")
            val newFavourite = !product.isFavoured
            val newProduct = product.copy(isFavoured = newFavourite)
            val newList = allProductsFlow.value.toMutableList()
            newList[indexOf] = newProduct
            Log.d("FEO410", "UPDATING $indexOf")
            allProductsFlow.emit(newList)
        }
    }

    override fun productsFlow(): StateFlow<List<Product>> {
        return allProductsFlow
    }
}