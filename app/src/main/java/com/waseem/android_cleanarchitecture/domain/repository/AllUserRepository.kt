package com.waseem.android_cleanarchitecture.domain.repository

import com.app.clean_architecture_kotlin.data.model.AllUsersResponse
import com.waseem.android_cleanarchitecture.data.model.cartsModel.CartsModel
import com.waseem.android_cleanarchitecture.data.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * interface to make an interaction between [AllUserRepositoryImpl] & [AllUserUseCase]
 * */
interface AllUserRepository {
    suspend fun allUsers(): Flow<Resource<AllUsersResponse>>

    suspend fun allCarts(): Flow<Resource<CartsModel>>
}