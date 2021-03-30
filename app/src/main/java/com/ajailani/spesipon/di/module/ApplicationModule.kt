package com.ajailani.spesipon.di.module

import com.ajailani.spesipon.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideBaseUrl() = "http://api-mobilespecs.azharimm.tk/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}