package com.waseem.android_cleanarchitecture.data.network

import com.app.clean_architecture_kotlin.data.model.LoginResponse
import com.app.clean_architecture_kotlin.data.model.AllUsersResponse
import com.waseem.android_cleanarchitecture.data.model.cartsModel.CartsModel
import com.waseem.android_cleanarchitecture.utils.extension.All_CARTS_EP
import com.waseem.android_cleanarchitecture.utils.extension.All_USERS_EP
import com.waseem.android_cleanarchitecture.utils.extension.LOGIN_EP
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST(LOGIN_EP)
    suspend fun loginUser(@Body request: RequestBody): Response<LoginResponse>

    @GET(All_USERS_EP)
    suspend fun getAllUsers(): Response<AllUsersResponse>

    @GET(All_CARTS_EP)
    suspend fun getAllCarts(): Response<CartsModel>
}