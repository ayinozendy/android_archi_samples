package com.gamalinda.android.poc.archisample.di

import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    private const val BASE_URL =
        "https://gist.githubusercontent.com/" +
                "ayinozendy/" +
                "a1f7629d8760c0d9cd4a5a4f051d111c/" +
                "raw/" +
                "7a7fcc0457e16dd9b8b2ac7865de972a95574102/"

    @Singleton
    @Provides
    fun provideVideoRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideVideoPlaylistService(
        retrofit: Retrofit
    ): VideoPlaylistService {
        return retrofit.create(VideoPlaylistService::class.java)
    }
}
