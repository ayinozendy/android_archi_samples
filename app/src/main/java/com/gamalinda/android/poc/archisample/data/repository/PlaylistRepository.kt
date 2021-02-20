package com.gamalinda.android.poc.archisample.data.repository

import com.gamalinda.android.poc.archisample.model.Playlist

interface PlaylistRepository {
    suspend fun fetchPlaylist()
    suspend fun getPlaylist(): Playlist
}
