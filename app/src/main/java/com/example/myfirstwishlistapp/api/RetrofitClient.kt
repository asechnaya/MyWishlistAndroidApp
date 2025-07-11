// app/src/main/java/com/example/myfirstwishlistapp/api/RetrofitClient.kt
package com.example.myfirstwishlistapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // IMPORTANT: Replace with your Django server's IP address or domain name
    // If running locally, use your machine's IP address (e.g., http://192.168.1.X:8000/)
    // NOT 127.0.0.1 as the Android emulator runs in its own virtual network.
    private const val BASE_URL = "http://10.0.2.2:8000/api/" // 10.0.2.2 is the emulator's localhost

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log request and response bodies
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // CORRECTED LINE: Initialize 'instance' using 'by lazy' as WishApiService
    val instance: WishApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WishApiService::class.java)
    }
}