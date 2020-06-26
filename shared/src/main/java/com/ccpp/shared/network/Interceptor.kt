package com.ccpp.shared.network

import com.ccpp.shared.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class Interceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val mainRequest = chain.request()
        val builder = mainRequest.newBuilder()
            .header("x-api-key", "2tnFcmn5Lk-a7xwmazAF")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .method(mainRequest.method(), mainRequest.body())

        return chain.proceed(builder.build())
    }
}