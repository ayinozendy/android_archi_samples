package com.gamalinda.android.poc.archisample.di

import com.gamalinda.android.poc.shared.data.repository.PlaylistRepository
import com.gamalinda.android.poc.shared.data.repository.PlaylistRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.*
import kmm.queries.shared.VideoItemQueries

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun providePlaylistRepository(
        ktorHttpClient: HttpClient,
        videoDao: VideoItemQueries
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(ktorHttpClient, videoDao)
    }
}
