package com.app.clean_architecture_kotlin.data.network

import com.waseem.android_cleanarchitecture.data.network.ApiService
import com.waseem.android_cleanarchitecture.data.network.BaseDataSource
import okhttp3.RequestBody

class RemoteDataSource(
    private val apiService: ApiService
) : BaseDataSource() {

    suspend fun loginUser(
        request: RequestBody
    ) = getResult {
        apiService.loginUser(
            request = request
        )
    }

    suspend fun getAllUsers() = getResult { apiService.getAllUsers() }

    suspend fun getAllCarts() = getResult { apiService.getAllCarts() }

}