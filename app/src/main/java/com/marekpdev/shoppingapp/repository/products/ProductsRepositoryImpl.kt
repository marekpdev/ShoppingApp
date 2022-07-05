package com.marekpdev.shoppingapp.repository.products

import android.util.Log
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data
import io.reactivex.rxjava3.core.Observable
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
class ProductsRepositoryImpl @Inject constructor(
   // private val productsApi: ProductsApi,
    //private val productsDao: ProductsDao
): ProductsRepository {

    private val allProducts = Data.getMenu().second!!

    override fun getProduct(id: Long): Observable<Product> {
        return Observable.just(Data.getProduct(1, 1))
    }

    override fun getProducts(): Observable<List<Product>> {
//        return Observable.just(allProducts)
//            //.delay(2, TimeUnit.SECONDS)
        return Observable.create { emitter ->
            try {
                Thread.sleep(2000)
            } catch (e: Exception){
                Log.d("FEO170", "ex $e")
            }
            emitter.onNext(allProducts)
            emitter.onComplete()
        }
    }
}