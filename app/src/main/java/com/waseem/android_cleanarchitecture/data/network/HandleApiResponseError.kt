package com.waseem.android_cleanarchitecture.data.network

import android.content.Context
import android.util.Log
import com.waseem.android_cleanarchitecture.BuildConfig
import com.waseem.android_cleanarchitecture.R

object HandleApiResponseError {

    const val showTAG = true
    const val TAG = "HandleApiResponse"

//    suspend fun <T : Any> handleApiCall(apiCall: suspend () -> Response<T>): Flow<BaseResponseResult<T, Any>> {
//        return flow {
//            try {
//                val response = apiCall()
//                if (response.isSuccessful) {
//                    emit(BaseResponseResult.Success(response.body()))
//                } else {
//                    val errorResponse: ErrorModel? = Util.convertErrorBody(response.errorBody())
//                    showTAGMsg(errorResponse?.message?:"",30)
//                    emit(BaseResponseResult.Error(errorResponse?.message ?: "Unknown error"))
//                }
//            } catch (e: IllegalStateException) {
//                showTAGMsg(e.localizedMessage,34)
//
//                emit(BaseResponseResult.Error(Constants.ILLEGAL_STATE_EXCEPTION))
//            } catch (e: HttpException) {
//                showTAGMsg(e.localizedMessage,38)
//
//                emit(BaseResponseResult.Error(Constants.NETWORK_ERROR_EXCEPTION))
//            } catch (e: UnknownHostException) {
//                showTAGMsg(e.localizedMessage,42)
//
//                emit(BaseResponseResult.Error(Constants.UNKNOWN_HOST_EXCEPTION))
//            } catch (e: SocketTimeoutException) {
//                showTAGMsg(e.localizedMessage,46)
//
//                emit(BaseResponseResult.Error(Constants.CHECK_YOUR_INTERNET_CONNECTION))
//            } catch (e: Exception) {
//                showTAGMsg(e.localizedMessage,50)
//
//                emit(BaseResponseResult.Error(e.message ?: "Unknown error"))
//            }
//        }.flowOn(Dispatchers.IO)
//    }


    fun handApiErrorResponse(context: Context, code: Int?):String {
        return  when (code) {
            401 -> context.getString(R.string.unauthorized_user)
            403 -> context.getString(R.string.forbidden)
            404 -> context.getString(R.string.not_found)
            429 -> context.getString(R.string.internet_not_available)
            400 -> context.getString(R.string.bad_request)
            500 -> context.getString(R.string.internal_server_error)
            503 -> context.getString(R.string.service_unavailable)
            else -> context.getString(R.string.unknown_error)
        }
    }

    private fun showTAGMsg(e: String?, i: Int) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "showTAGMsg: $i  msg = $e")
        }
    }
}