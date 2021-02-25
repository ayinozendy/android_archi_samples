package com.gamalinda.android.poc.shared.data.mapper

import com.gamalinda.android.poc.shared.model.Video
import kmm.queries.shared.VideoItem

class VideoEntityDataMapper {
    companion object {
        fun toSqlDelightEntity(video: Video): VideoItem {
            return VideoItem(
                id = video.id.toString(),
                title = video.title,
                author = video.author,
                description = video.description,
                videoUrl = video.videoUrl,
                thumbnailUrl = video.thumbnailUrl
            )
        }

        private fun fromSqlDelightEntityToModel(videoItem: VideoItem): Video {
            return Video(
                id = videoItem.id.toInt(),
                title = videoItem.title,
                author = videoItem.author,
                description = videoItem.description,
                videoUrl = videoItem.videoUrl,
                thumbnailUrl = videoItem.thumbnailUrl
            )
        }

        fun fromSqlDelightEntityToModels(videoEntityEntities: List<VideoItem>): List<Video> {
            return videoEntityEntities.map {
                fromSqlDelightEntityToModel(it)
            }
        }
    }
}
