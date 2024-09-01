package com.waseem.android_cleanarchitecture.di

import com.app.clean_architecture_kotlin.data.network.RemoteDataSource
import com.waseem.android_cleanarchitecture.data.network.provideApi
import com.waseem.android_cleanarchitecture.data.network.provideRetrofit
import com.waseem.android_cleanarchitecture.data.repository.AllUserRepositoryImpl
import com.waseem.android_cleanarchitecture.data.repository.LoginRepositoryImpl
import com.waseem.android_cleanarchitecture.domain.repository.AllUserRepository
import com.waseem.android_cleanarchitecture.domain.repository.LoginRepository
import com.waseem.android_cleanarchitecture.domain.usecase.AllUserUseCase
import com.waseem.android_cleanarchitecture.presentation.viewmodel.AllUserViewModel
import com.waseem.android_cleanarchitecture.presentation.viewmodel.LoginViewModel
import com.waseem.android_cleanarchitecture.domain.usecase.LoginUseCase
import com.waseem.android_cleanarchitecture.utils.helper.PreferenceHelper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    // declaration of singleton instances
    single { provideRetrofit() }
    single { PreferenceHelper(get()) }

    factory { provideApi(get()) }
    factory { RemoteDataSource(get()) }

    // repositories
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<AllUserRepository> { AllUserRepositoryImpl(get()) }

    // Use cases
    factory { LoginUseCase(get()) }
    factory { AllUserUseCase(get()) }

    // View models
    viewModel { LoginViewModel(get(), get()) }
    viewModel { AllUserViewModel(get(), get()) }
}