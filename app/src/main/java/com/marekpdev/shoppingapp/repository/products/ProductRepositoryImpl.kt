package com.marekpdev.shoppingapp.repository.products

import com.marekpdev.shoppingapp.api.ProductsApi
import com.marekpdev.shoppingapp.db.ProductsDao
import com.marekpdev.shoppingapp.di.AppScope
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */

@AppScope
class ProductRepositoryImpl @Inject constructor(
   // private val productsApi: ProductsApi,
    //private val productsDao: ProductsDao
): ProductsRepository {

    override fun observeProducts(): Observable<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun observeProduct(): Observable<Product> {
        return Observable.just(createProduct(1))
    }

    // DEBUG
     fun createProduct(id: Long) = Product(
        id = id,
        name = "Product $id",
        description = "Pinstripped cornflower blue cotton blouse takes you on a walk to the park or just down the hall.",
        price = id * 1.11,
        oldPrice = id * 1.44,
        currency = "$",
        availableColors = listOf(
            Color(1, "light sea green", "#17C3B2"),
            Color(2, "CG Blue", "#227C9D"),
            Color(3, "maximum yellow red", "#FFCB77")
        ),
        availableSizes = (1..9).map { Size(it, "0$it") },
        images = listOf(
            "https://cdn.shopify.com/s/files/1/0932/1794/files/Artboard_24_370x230@2x.png?v=1605584742",
            "https://romans-cdn.rlab.net/images/extralarge/350f24c0-9476-465b-9c8a-5e0a70b6bf62.jpg",
            "https://cdn.foreverunique.com/products/uar212729_4205.jpg?h=480&w=",
            "https://media.sezane.com/image/upload/c_crop,fl_progressive:semi,h_0.95333333333333,q_auto:best,w_1,x_0,y_0.023333333333333/c_scale,w_598/whb5logixhjjmnqrurfp.jpg"
        ),
        categoryId = 1L
    )

    fun getProducts(count: Int): List<Product>{
        return (1..count).map { createProduct(it.toLong()) }
    }
}