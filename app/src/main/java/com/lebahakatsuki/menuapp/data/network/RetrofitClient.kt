package com.lebahakatsuki.menuapp.data.network

import com.lebahakatsuki.menuapp.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.internal.connection.ConnectInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val contentType = Interceptor {
        val originalRequest = it.request()
        val authenticationRequest = originalRequest.newBuilder()
                .header("Content-Type", "application/json")
                .build()

        it.proceed(authenticationRequest)
    }

    private val httpClientBuilder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0,5, TimeUnit.MINUTES))
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(contentType)
            .addInterceptor(logging).build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClientBuilder)
        .build()
}