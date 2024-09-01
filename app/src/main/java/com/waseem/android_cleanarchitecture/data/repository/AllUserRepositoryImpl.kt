package com.waseem.android_cleanarchitecture.data.repository

import com.app.clean_architecture_kotlin.data.model.AllUsersResponse
import com.app.clean_architecture_kotlin.data.network.RemoteDataSource
import com.waseem.android_cleanarchitecture.data.model.cartsModel.CartsModel
import com.waseem.android_cleanarchitecture.data.network.Resource
import com.waseem.android_cleanarchitecture.domain.repository.AllUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for fetching data[AllUsersResponse] from server
 * */
class AllUserRepositoryImpl(private val remoteDataSource: RemoteDataSource) : AllUserRepository {

    override suspend fun allUsers(): Flow<Resource<AllUsersResponse>> {
        return flow {
            emit(Resource.loading(null))
            val response = remoteDataSource.getAllUsers()
            emit(response)
        }
    }

    override suspend fun allCarts(): Flow<Resource<CartsModel>> {
        return flow {
            emit(Resource.loading(null))
            val response = remoteDataSource.getAllCarts()
            emit(response)
        }
    }

}