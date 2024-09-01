package com.waseem.android_cleanarchitecture.data.network

import com.google.gson.GsonBuilder
import com.waseem.android_cleanarchitecture.utils.extension.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY  // Choose the level of logging you need (BODY, HEADERS, BASIC, NONE)
    }


    val okHttpClient = OkHttpClient().newBuilder()
        .callTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(AuthInterceptor())
        .addInterceptor(loggingInterceptor) // Add the logging interceptor
        .build()

    return Retrofit.Builder().baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
}
fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)