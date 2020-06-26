
package com.ccpp.shared.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ApiService
@Inject constructor(
    private val sessions: Sessions,
    loggingInterceptor: HttpLoggingInterceptor,
    authorizationInterceptor: AuthorizationInterceptor
    ) {

    private val loginNetworkApi = Retrofit.Builder()
        .baseUrl("https://dl.dropboxusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient(authorizationInterceptor, loggingInterceptor))
        .build().create(ApiClient::class.java)

    private fun okHttpClient(authorizationInterceptor: AuthorizationInterceptor, loggingInterceptor: HttpLoggingInterceptor) :OkHttpClient  {
        val client = OkHttpClient.Builder()
        client.followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(loggingInterceptor)


        return client.build();
    }


    fun getList() = loginNetworkApi.getList()


}
