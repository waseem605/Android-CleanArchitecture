package com.waseem.android_cleanarchitecture.domain.usecase

import com.app.clean_architecture_kotlin.data.model.LoginResponse
import com.waseem.android_cleanarchitecture.data.network.Resource
import com.waseem.android_cleanarchitecture.domain.repository.LoginRepository
import com.waseem.android_cleanarchitecture.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.Flow

/**
 * An interactor class that executes the implementation of [LoginViewModel]
 * It handles user authentication and stores token
 */
class LoginUseCase(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(username: String, pass: String): Flow<Resource<LoginResponse>> {
        return loginRepository.loginUser(username, pass)
    }
}