package com.waseem.android_cleanarchitecture.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.clean_architecture_kotlin.data.model.AllUsersResponse
import com.waseem.android_cleanarchitecture.R
import com.waseem.android_cleanarchitecture.data.model.cartsModel.CartsModel
import com.waseem.android_cleanarchitecture.data.network.HandleApiResponseError
import com.waseem.android_cleanarchitecture.data.network.Status
import com.waseem.android_cleanarchitecture.domain.usecase.AllUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class AllUserViewModel (
    private val allUserUseCase: AllUserUseCase,
    private val context: Context
    ) : ViewModel() {
        val allUserDataFlow = MutableSharedFlow<AllUsersResponse>()
        val allCartsDataFlow = MutableSharedFlow<CartsModel>()

        private val _apiErrorToast = MutableLiveData<String>()
        val apiErrorToast: LiveData<String> = _apiErrorToast

        private val _waitForServer = MutableLiveData(false)
        val waitForServer: LiveData<Boolean> = _waitForServer

        fun getAllUsers() {
            viewModelScope.launch(Dispatchers.IO) {
                allUserUseCase.invoke().collect { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            if (resource.data != null) {
                                resource.data.let {
                                    allUserDataFlow.emit(it)
                                    _waitForServer.postValue(false)
                                }
                            } else {
                                _apiErrorToast.postValue(context.getString(R.string.something_went_wrong))
                                _waitForServer.postValue(false)
                            }
                        }

                        Status.LOADING -> {
                            _waitForServer.postValue(true)
                        }

                        Status.ERROR -> {
                            val errorMessage =
                                HandleApiResponseError.handApiErrorResponse(context, resource.code)
                            _apiErrorToast.postValue(errorMessage)
                            _waitForServer.postValue(false)
                        }
                    }
                }
            }
        }


        fun getAllCArts() {
            viewModelScope.launch(Dispatchers.IO) {
                allUserUseCase.getCarts().collect { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            if (resource.data != null) {
                                resource.data.let {
                                    allCartsDataFlow.emit(it)
                                    _waitForServer.postValue(false)
                                }
                            } else {
                                _apiErrorToast.postValue(context.getString(R.string.something_went_wrong))
                                _waitForServer.postValue(false)
                            }
                        }

                        Status.LOADING -> {
                            _waitForServer.postValue(true)
                        }

                        Status.ERROR -> {
                            val errorMessage = HandleApiResponseError.handApiErrorResponse(context, resource.code)
                            _apiErrorToast.postValue(errorMessage)
                            _waitForServer.postValue(false)
                        }
                    }
                }
            }
        }
}