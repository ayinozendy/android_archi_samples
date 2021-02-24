package com.gamalinda.android.poc.archisample.data.persistence.mapper

import com.gamalinda.android.poc.archisample.data.persistence.entity.VideoEntity
import com.gamalinda.android.poc.archisample.model.Video
import kmm.queries.shared.VideoItem

sealed class VideoEntityDataMapper {
    companion object {
        private fun toEntity(video: Video): VideoEntity {
            return VideoEntity(
                id = video.id,
                title = video.title,
                author = video.author,
                description = video.description,
                videoUrl = video.videoUrl,
                thumbnailUrl = video.thumbnailUrl
            )
        }

        fun toEntities(videos: List<Video>): List<VideoEntity> {
            return videos.map {
                toEntity(it)
            }
        }

        private fun toModel(videoEntity: VideoEntity): Video {
            return Video(
                id = videoEntity.id,
                title = videoEntity.title,
                author = videoEntity.author,
                description = videoEntity.description,
                videoUrl = videoEntity.videoUrl,
                thumbnailUrl = videoEntity.thumbnailUrl
            )
        }

        fun toModels(videoEntityEntities: List<VideoEntity>): List<Video> {
            return videoEntityEntities.map {
                toModel(it)
            }
        }

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
