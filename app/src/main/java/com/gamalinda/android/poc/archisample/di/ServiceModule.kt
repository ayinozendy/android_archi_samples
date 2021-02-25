package com.gamalinda.android.poc.archisample.di

import com.gamalinda.android.poc.shared.data.service.ApiClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideKtorHttpClient(): HttpClient {
        return ApiClientFactory().createClient()
    }
}
