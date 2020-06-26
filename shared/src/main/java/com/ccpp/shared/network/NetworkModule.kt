
package com.ccpp.shared.network

import com.ccpp.shared.BuildConfig
import com.ccpp.shared.network.repository.prefs.*
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideSessions(): Sessions {
        return Sessions()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        interceptor: com.ccpp.shared.network.Interceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()

        client.followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)

        return client.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    @Singleton
    fun provideAuthorizationInterceptor(sessions: Sessions): Interceptor {
        return AuthorizationInterceptor(sessions)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor()
    }

//    @Provides
//    @Singleton
//    fun provideValidateMobileTokenRepository(dataSource: ValidateMobileTokenRepository.Network): ValidateMobileTokenRepository = dataSource


    @Provides
    @Singleton
    fun provideLListRepository(dataSource: ListRepository.Network): ListRepository =
        dataSource

}
