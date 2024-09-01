package com.waseem.android_cleanarchitecture.domain.usecase

import com.app.clean_architecture_kotlin.data.model.AllUsersResponse
import com.waseem.android_cleanarchitecture.data.model.cartsModel.CartsModel
import com.waseem.android_cleanarchitecture.data.network.Resource
import com.waseem.android_cleanarchitecture.domain.repository.AllUserRepository
import com.waseem.android_cleanarchitecture.presentation.viewmodel.AllUserViewModel
import kotlinx.coroutines.flow.Flow

/**
 * An interactor class that executes the implementation of [AllUserViewModel]
 * It handles data responses and manages a list of users.
 */

class AllUserUseCase(private val allUserRepository: AllUserRepository) {

    suspend operator fun invoke(): Flow<Resource<AllUsersResponse>> {
        return allUserRepository.allUsers()
    }
    suspend fun getCarts(): Flow<Resource<CartsModel>> {
        return allUserRepository.allCarts()
    }

}