package com.gamalinda.android.poc.archisample.data.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gamalinda.android.poc.archisample.data.persistence.dao.VideoDao
import com.gamalinda.android.poc.archisample.data.persistence.entity.VideoEntity

@Database(
    version = 1,
    entities = [VideoEntity::class]
)
abstract class MainRoomDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}
