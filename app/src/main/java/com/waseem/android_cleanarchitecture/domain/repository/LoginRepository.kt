package com.waseem.android_cleanarchitecture.domain.repository

import com.app.clean_architecture_kotlin.data.model.LoginResponse
import com.waseem.android_cleanarchitecture.data.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * interface to make an interaction between [LoginRepositoryImpl] & [LoginUseCase]
 * */
interface LoginRepository {
    suspend fun loginUser(username: String, password: String): Flow<Resource<LoginResponse>>
}