package com.gamalinda.android.poc.archisample.data.repository.impl

import com.gamalinda.android.poc.archisample.data.persistence.mapper.VideoEntityDataMapper
import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import com.gamalinda.android.poc.archisample.model.Playlist
import kmm.queries.shared.VideoItemQueries

class PlaylistRepositoryImpl(
    private val videoPlaylistService: VideoPlaylistService,
    private val videoDao: VideoItemQueries
) : PlaylistRepository {
    override suspend fun fetchPlaylist() {
        val newPlaylist = videoPlaylistService.getVideos()
        if (newPlaylist.videos.isNullOrEmpty()) {
            throw Exception("Null Playlist")
        } else {
            saveNewPlaylist(newPlaylist)
        }
    }

    private fun saveNewPlaylist(newPlaylist: Playlist) {
        videoDao.deleteAll()
        newPlaylist.videos.forEach {
            videoDao.insertItem(VideoEntityDataMapper.toSqlDelightEntity(it))
        }
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

    private fun retrievePlaylist(): Playlist {
        val entities = videoDao.getAll()
        val videos = VideoEntityDataMapper.fromSqlDelightEntityToModels(entities.executeAsList())
        return Playlist(videos)
    }
}
