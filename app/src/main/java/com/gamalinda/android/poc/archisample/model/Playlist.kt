package com.gamalinda.android.poc.archisample.model

import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("play_list") val videos: List<Video>
)
