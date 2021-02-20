package com.gamalinda.android.poc.archisample.data.repository.impl

import com.gamalinda.android.poc.archisample.MainApplication
import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.model.Playlist

class PlaylistRepositoryImpl(private val mainApplication: MainApplication) : PlaylistRepository {
    override suspend fun fetchPlaylist() {
        val newPlaylist = mainApplication.getPlaylistService().getVideos()
        if (newPlaylist.videos.isNullOrEmpty()) {
            throw Exception("Null Playlist")
        } else {
            mainApplication.saveNewPlaylist(newPlaylist)
        }
    }

    override suspend fun getPlaylist(): Playlist {
        val playlist = mainApplication.getPlaylist()
        return if (playlist.videos.isNullOrEmpty()) {
            try {
                fetchPlaylist()
            } finally {
                //Do nothing
            }
            mainApplication.getPlaylist()
        } else {
            playlist
        }
    }
}
