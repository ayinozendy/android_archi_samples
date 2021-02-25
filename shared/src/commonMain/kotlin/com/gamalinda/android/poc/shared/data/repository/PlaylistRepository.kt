package com.gamalinda.android.poc.shared.data.repository

import com.gamalinda.android.poc.shared.model.Playlist

interface PlaylistRepository {
    suspend fun fetchPlaylist()
    suspend fun getPlaylist(): Playlist
}
