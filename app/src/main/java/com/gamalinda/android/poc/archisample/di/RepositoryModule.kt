package com.gamalinda.android.poc.archisample.di

import com.gamalinda.android.poc.archisample.data.persistence.dao.VideoDao
import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.repository.impl.PlaylistRepositoryImpl
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun providePlaylistRepository(
        videoPlaylistService: VideoPlaylistService,
        videoDao: VideoDao
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(videoPlaylistService, videoDao)
    }
}
