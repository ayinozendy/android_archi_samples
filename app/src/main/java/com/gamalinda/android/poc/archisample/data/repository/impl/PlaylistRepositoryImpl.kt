package com.gamalinda.android.poc.archisample.data.repository.impl

import com.gamalinda.android.poc.archisample.data.persistence.dao.VideoDao
import com.gamalinda.android.poc.archisample.data.persistence.mapper.VideoEntityDataMapper
import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import com.gamalinda.android.poc.archisample.model.Playlist

class PlaylistRepositoryImpl(
    private val videoPlaylistService: VideoPlaylistService,
    private val videoDao: VideoDao
) : PlaylistRepository {
    override suspend fun fetchPlaylist() {
        val newPlaylist = videoPlaylistService.getVideos()
        if (newPlaylist.videos.isNullOrEmpty()) {
            throw Exception("Null Playlist")
        } else {
            saveNewPlaylist(newPlaylist)
        }
    }

    private suspend fun saveNewPlaylist(newPlaylist: Playlist) {
        videoDao.deleteAll()
        val videoEntities = VideoEntityDataMapper.toEntities(newPlaylist.videos).toTypedArray()
        videoDao.insertAll(*videoEntities)
    }

    override suspend fun getPlaylist(): Playlist {
        val playlist = retrievePlaylist()
        return if (playlist.videos.isNullOrEmpty()) {
            try {
                fetchPlaylist()
            } finally {
                //Do nothing
            }
            retrievePlaylist()
        } else {
            playlist
        }
    }

    private suspend fun retrievePlaylist(): Playlist {
        val entities = videoDao.getAll()
        val videos = VideoEntityDataMapper.toModels(entities)
        return Playlist(videos)
    }
}
