package com.gamalinda.android.poc.shared.data.repository

import com.gamalinda.android.poc.shared.model.Playlist
import kmm.queries.shared.KmmAppDatabase
import kmm.queries.shared.VideoItemQueries

actual class PlaylistRepositoryImpl(
    private val videoItemQueries: VideoItemQueries,
    private val kmmAppDatabase: KmmAppDatabase
) : PlaylistRepository {
    override suspend fun fetchPlaylist() {
    }

    override suspend fun getPlaylist(): Playlist {
        return Playlist(videos = listOf())
    }
}
