package com.gamalinda.android.poc.archisample.di

import android.app.Application
import com.gamalinda.android.poc.archisample.MainApplication
import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.repository.impl.PlaylistRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun providePlaylistRepository(
        application: Application
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(application as MainApplication)
    }
}
