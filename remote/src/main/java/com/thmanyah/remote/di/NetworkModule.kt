package com.thmanyah.remote.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.thmanyah.common.BuildConfig
import com.thmanyah.remote.api.ApiService
import com.thmanyah.remote.interceptor.ExceptionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECTION_TIME_OUT = 2L
    private const val READ_TIME_OUT = 2L
    private const val WRITE_TIME_OUT = 2L

    @Singleton
    @Provides
    fun provideNetworkClient(
        chuckerInterceptor: ChuckerInterceptor
    ): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MINUTES)
            .readTimeout(READ_TIME_OUT, TimeUnit.MINUTES)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
            .addInterceptor(ExceptionInterceptor())

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(chuckerInterceptor)
        }

        return Retrofit.Builder()
            .baseUrl("https://api-v2-b2sit6oh3a-uc.a.run.app/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }



    @Singleton
    @Provides
    fun provideNewService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideChucker(
        @ApplicationContext context: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }
}