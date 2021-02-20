package com.gamalinda.android.poc.archisample

import android.app.Application
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import com.gamalinda.android.poc.archisample.model.Playlist
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        const val BASE_URL =
            "https://gist.githubusercontent.com/" +
                    "ayinozendy/" +
                    "a1f7629d8760c0d9cd4a5a4f051d111c/" +
                    "raw/" +
                    "7a7fcc0457e16dd9b8b2ac7865de972a95574102/"
    }

    private lateinit var playlistService: VideoPlaylistService
    private var playlistItemsMemCache = Playlist(listOf())

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        playlistService = retrofit.create(VideoPlaylistService::class.java)
    }

    fun getPlaylistService(): VideoPlaylistService {
        return playlistService
    }

    fun getPlaylist() = playlistItemsMemCache

    fun saveNewPlaylist(newPlaylist: Playlist) {
        playlistItemsMemCache = newPlaylist
    }
}
