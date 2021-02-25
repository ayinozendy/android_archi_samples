package com.gamalinda.android.poc.archisample.di

import android.app.Application
import com.gamalinda.android.poc.shared.DriverFactory
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kmm.queries.shared.KmmAppDatabase
import kmm.queries.shared.VideoItemQueries
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideSqlDriver(
        application: Application
    ): SqlDriver {
        return DriverFactory(application).createDriver()
    }

    @Singleton
    @Provides
    fun provideKmmDatabase(
        sqlDriver: SqlDriver
    ): KmmAppDatabase {
        return KmmAppDatabase(sqlDriver)
    }

    @Provides
    fun provideVideoItemQueries(
        database: KmmAppDatabase
    ): VideoItemQueries {
        return database.videoItemQueries
    }
}
