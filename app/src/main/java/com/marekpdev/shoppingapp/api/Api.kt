package com.marekpdev.shoppingapp.api

import com.marekpdev.shoppingapp.models.Product
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Marek Pszczolka on 10/07/2021.
 */
interface Api {

    @GET("products/getAll")
    fun getAllProducts(): Single<ProductsResponse>

    @POST("/")
    fun sayHello(): Single<Any>


}