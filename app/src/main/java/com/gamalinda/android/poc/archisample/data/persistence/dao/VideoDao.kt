package com.gamalinda.android.poc.archisample.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gamalinda.android.poc.archisample.data.persistence.entity.VideoEntity

@Dao
interface VideoDao {

    @Query("SELECT * FROM videos")
    suspend fun getAll(): List<VideoEntity>

    @Query("SELECT * FROM videos WHERE id = :videoId")
    suspend fun findById(videoId: Int): VideoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg videoEntityItems: VideoEntity)

    @Query("DELETE FROM videos")
    suspend fun deleteAll()
}
