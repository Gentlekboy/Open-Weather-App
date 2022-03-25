package com.gentlekboy.openweatherapp.di

import com.gentlekboy.openweatherapp.data.network.ApiInterface
import com.gentlekboy.openweatherapp.repository.Repository
import com.gentlekboy.openweatherapp.repository.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(apiInterface: ApiInterface): RepositoryInterface =
        Repository(apiInterface)
}