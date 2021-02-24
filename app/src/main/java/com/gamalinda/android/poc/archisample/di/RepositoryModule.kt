package com.gamalinda.android.poc.archisample.di

import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.repository.impl.PlaylistRepositoryImpl
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kmm.queries.shared.VideoItemQueries

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun providePlaylistRepository(
        videoPlaylistService: VideoPlaylistService,
        videoDao: VideoItemQueries
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(videoPlaylistService, videoDao)
    }
}
