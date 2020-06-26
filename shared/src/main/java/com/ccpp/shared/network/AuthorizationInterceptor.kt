/*
 * Created by Chaiwut Maneechot on 10/2/18 9:36 AM
 * Copyright © 2018 2C2P. All rights reserved.
 * Last modified 10/1/18 4:03 PM
 */

package com.ccpp.shared.network

import com.ccpp.shared.util.ConstantsBase
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(private val sessions: Sessions) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var mainResponse = chain.proceed(chain.request())
        val mainRequest = chain.request()
//
        val contentLength = mainResponse.body()!!.contentLength()

        val source = mainResponse.body()!!.source()
        source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
        val buffer = source.buffer()

        return mainResponse
    }
}