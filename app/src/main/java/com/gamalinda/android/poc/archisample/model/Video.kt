package com.gamalinda.android.poc.archisample.model

import com.google.gson.annotations.SerializedName

data class Video(
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String,
    @SerializedName("video_url") val videoUrl: String
)
