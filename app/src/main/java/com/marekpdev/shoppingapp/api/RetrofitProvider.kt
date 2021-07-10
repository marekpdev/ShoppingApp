package com.marekpdev.shoppingapp.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Marek Pszczolka on 10/07/2021.
 */
class RetrofitProvider {

    companion object {
        // https://stackoverflow.com/questions/5495534/java-net-connectexception-localhost-127-0-0-18080-connection-refused
        private val BASE_URL =
//            "http://localhost:8080/"
            "http://10.0.2.2:8080/"

        private var retrofit: Retrofit? = null

        /** Returns an instance of `Retrofit`. */
        fun getRetrofit(): Retrofit =
            retrofit ?: createRetrofit().also { retrofit = it }

        private fun createRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createHttpClient())
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()


        private fun createHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()

        fun provideGson(): Gson {
            return GsonBuilder()
                .setLenient()
                .create()
        }

        fun getApi(): Api {
            return getRetrofit().create(Api::class.java)
        }

    }




}