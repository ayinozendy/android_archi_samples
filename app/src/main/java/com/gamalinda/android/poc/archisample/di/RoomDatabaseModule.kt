package com.gamalinda.android.poc.archisample.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gamalinda.android.poc.archisample.data.persistence.dao.VideoDao
import com.gamalinda.android.poc.archisample.data.persistence.db.MainRoomDatabase
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
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideMainRoomDatabase(
        application: Application
    ): RoomDatabase {
        return Room.databaseBuilder(
            application,
            MainRoomDatabase::class.java,
            "main_room_database"
        ).build()
    }

    @Provides
    fun provideVideoDao(
        roomDatabase: RoomDatabase
    ): VideoDao {
        return (roomDatabase as MainRoomDatabase).videoDao()
    }

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
