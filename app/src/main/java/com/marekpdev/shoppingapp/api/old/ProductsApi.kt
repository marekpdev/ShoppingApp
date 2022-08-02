package com.marekpdev.shoppingapp.api.old

import com.marekpdev.shoppingapp.domain.ProductsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Created by Marek Pszczolka on 10/07/2021.
 */
interface ProductsApi {

    @GET("products/getAll")
    fun getAllProducts(): Single<ProductsResponse>
    
}