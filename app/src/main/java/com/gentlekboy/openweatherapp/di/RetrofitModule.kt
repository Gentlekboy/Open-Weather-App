package com.gentlekboy.openweatherapp.di

import com.gentlekboy.openweatherapp.data.network.ApiInterface
import com.gentlekboy.openweatherapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(
        httpLoggingInterceptor: Call.Factory,
        moshiConverterFactory: MoshiConverterFactory,
        baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .callFactory(httpLoggingInterceptor)
        .addConverterFactory(moshiConverterFactory)
        .baseUrl(baseUrl)
        .build()

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
}