package com.gamalinda.android.poc.archisample.data.repository.impl

import com.gamalinda.android.poc.archisample.data.persistence.dao.VideoDao
import com.gamalinda.android.poc.archisample.data.persistence.entity.VideoEntity
import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import com.gamalinda.android.poc.archisample.model.Playlist
import com.gamalinda.android.poc.archisample.model.Video
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import net.bytebuddy.utility.RandomString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlaylistRepositoryImplTest : TestCase() {

    @Mock
    private lateinit var videoPlaylistService: VideoPlaylistService

    @Mock
    private lateinit var videoDao: VideoDao

    private lateinit var playlistRepository: PlaylistRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        playlistRepository = PlaylistRepositoryImpl(videoPlaylistService, videoDao)
    }

    @Test
    fun testFetchPlaylist() {
        val expected = Playlist(
            listOf(
                Video(
                    id = 0,
                    title = RandomString.make(2),
                    author = RandomString.make(2),
                    description = RandomString.make(2),
                    videoUrl = RandomString.make(2),
                    thumbnailUrl = RandomString.make(2)
                )
            )
        )
        runBlocking {
            `when`(videoPlaylistService.getVideos()).thenReturn(expected)
            playlistRepository.fetchPlaylist()
            verify(videoPlaylistService, times(1)).getVideos()
        }
    }

    @Test
    fun testGetPlaylist() {
        val expected = listOf(
            VideoEntity(
                id = 0,
                title = RandomString.make(2),
                author = RandomString.make(2),
                description = RandomString.make(2),
                videoUrl = RandomString.make(2),
                thumbnailUrl = RandomString.make(2)
            )
        )
        runBlocking {
            `when`(videoDao.getAll()).thenReturn(expected)
            playlistRepository.getPlaylist()
            verify(videoDao, times(1)).getAll()
        }
    }
}
