package com.gamalinda.android.poc.archisample.data.service

import com.gamalinda.android.poc.archisample.model.Playlist
import retrofit2.http.GET

interface VideoPlaylistService {

    @GET("playlist.json")
    suspend fun getVideos(): Playlist
}
