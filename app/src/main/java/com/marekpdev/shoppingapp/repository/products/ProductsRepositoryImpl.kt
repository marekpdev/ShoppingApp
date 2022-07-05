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

    private val allProducts = Data.products

    override fun getProduct(id: Long): Observable<Product> {
        return Observable.create { emitter ->

            try {
                Thread.sleep(2000)
            } catch (e: Exception){
                Log.d("FEO170", "ex $e")
            }

            Log.d("FEO400", "Finding id $id")
            Log.d("FEO400", "All ids: " + allProducts.map { product -> product.id }.toString())
            val product = allProducts.find {
                Log.d("FEO400", "This id ${it.id} - searched $id")
                it.id == id
            }
            if(product != null) emitter.onNext(product)
            else emitter.onError(Exception("Product not found"))
            emitter.onComplete()
        }
    }

    override fun getProducts(): Observable<List<Product>> {
//        return Observable.just(allProducts)
//            //.delay(2, TimeUnit.SECONDS)
        return Observable.create { emitter ->
            try {
                Thread.sleep(200)
            } catch (e: Exception){
                Log.d("FEO170", "ex $e")
            }
            emitter.onNext(allProducts)
            emitter.onComplete()
        }
    }
}