package com.gamalinda.android.poc.archisample.data.repository.impl

import com.gamalinda.android.poc.archisample.data.persistence.mapper.VideoEntityDataMapper
import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import com.gamalinda.android.poc.archisample.model.Playlist
import io.ktor.client.*
import io.ktor.client.request.*
import kmm.queries.shared.VideoItemQueries

class PlaylistRepositoryImpl(
    private val ktorHttpClient: HttpClient,
    private val videoDao: VideoItemQueries
) : PlaylistRepository {
    override suspend fun fetchPlaylist() {
        val newPlaylist = ktorHttpClient.get<Playlist> {
            url("https://gist.githubusercontent.com/" +
                    "ayinozendy/" +
                    "a1f7629d8760c0d9cd4a5a4f051d111c/" +
                    "raw/" +
                    "7a7fcc0457e16dd9b8b2ac7865de972a95574102/playlist.json")
        }

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
