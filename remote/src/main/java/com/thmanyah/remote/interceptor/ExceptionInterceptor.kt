package com.thmanyah.remote.interceptor

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.thmanyah.remote.exception.NetworkException
import com.thmanyah.remote.model.BaseResponse
import okhttp3.Interceptor
import okhttp3.Response

class ExceptionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val code = response.code

        if (code in 500..599 || code == 401) {
            val error = getNetworkException(response)
            response.close()
            throw error
        }
        return response
    }

    private fun getNetworkException(response: Response?): NetworkException {
        return try {
            if (response == null) return NetworkException()
            val responseBody = response.body ?: return NetworkException()
            val respString = String(responseBody.bytes())
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<BaseResponse> =
                moshi.adapter(BaseResponse::class.java)
            val error = jsonAdapter.fromJson(respString)
            NetworkException(error?.message)

        } catch (e: Throwable) {
            NetworkException()
        }
    }

}