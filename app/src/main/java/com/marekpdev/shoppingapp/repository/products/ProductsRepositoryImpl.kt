package com.marekpdev.shoppingapp.repository.products

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
class ProductsRepositoryImpl @Inject constructor(
   // private val productsApi: ProductsApi,
    //private val productsDao: ProductsDao
): ProductsRepository {

    private val allProducts = BehaviorSubject.createDefault<List<Product>>(Data.products.toList())

    init {
        Log.d("FEO440", "CREATING NEW ProductsRepositoryImpl")
    }

    override fun getProduct(id: Long): Single<Product> {
        return Single.create { emitter ->

            try {
                Thread.sleep(400)
            } catch (e: Exception){
                Log.d("FEO170", "ex $e")
            }
            val product = allProducts.value?.find {
                Log.d("FEO400", "This id ${it.id} - searched $id")
                it.id == id
            }
            if(product != null) emitter.onSuccess(product)
            else emitter.onError(Exception("Product not found"))
        }
    }

    override fun getProducts(): Single<List<Product>> {
        return Single.create { emitter ->
            try {
                Thread.sleep(200)
            } catch (e: Exception){
                Log.d("FEO170", "ex $e")
            }
            emitter.onSuccess(allProducts.value)
        }
    }

    override fun observeProducts(): Observable<List<Product>> {
        return allProducts
    }

    override fun toggleFavourite(product: Product): Completable {
        return Completable.create { emitter ->
            val indexOf = allProducts.value?.indexOf(product)
            if(indexOf != null) {
                Log.d("FEO410", "Index found $indexOf")
                val newFavourite = !product.isFavoured
                val newProduct = product.copy(isFavoured = newFavourite)
                val newList = allProducts.value!!.toMutableList()
                newList[indexOf] = newProduct
                Log.d("FEO410", "UPDATING $indexOf")
                allProducts.onNext(newList)
                emitter.onComplete()
            }
        }
    }
}