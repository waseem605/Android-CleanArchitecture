package com.waseem.android_cleanarchitecture.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.clean_architecture_kotlin.data.model.LoginResponse
import com.waseem.android_cleanarchitecture.R
import com.waseem.android_cleanarchitecture.data.network.HandleApiResponseError.handApiErrorResponse
import com.waseem.android_cleanarchitecture.data.network.Status
import com.waseem.android_cleanarchitecture.domain.usecase.LoginUseCase
import com.waseem.android_cleanarchitecture.presentation.ui.activity.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing and preparing data for UI [LoginActivity].
 *
 **/

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val context: Context
) : ViewModel() {
    val loginUserDataFlow = MutableSharedFlow<LoginResponse>()

    private val _apiErrorToast = MutableLiveData<String>()
    val apiErrorToast: LiveData<String> = _apiErrorToast

    val _waitForServer = MutableLiveData<Boolean>()
    val waitForServer: LiveData<Boolean> = _waitForServer

    fun loginUser(username: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.invoke(username, pass).collect { resource ->

                when (resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data != null) {
                            resource.data.let {
                                loginUserDataFlow.emit(it)
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
                      val errorMessage =  handApiErrorResponse(context,resource.code)
                        _apiErrorToast.postValue(errorMessage)

                        _waitForServer.postValue(false)
                    }
                }
            }
        }
    }
}