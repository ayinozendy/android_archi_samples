package com.gamalinda.android.poc.shared.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    @SerialName("video_url") val videoUrl: String
)
