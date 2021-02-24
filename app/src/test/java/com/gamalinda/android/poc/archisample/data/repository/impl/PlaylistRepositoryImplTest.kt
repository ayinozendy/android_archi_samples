package com.gamalinda.android.poc.archisample.data.repository.impl

import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import com.gamalinda.android.poc.archisample.model.Playlist
import com.gamalinda.android.poc.archisample.model.Video
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import junit.framework.TestCase
import kmm.queries.shared.KmmAppDatabase
import kmm.queries.shared.VideoItem
import kmm.queries.shared.VideoItemQueries
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
    private lateinit var sqlDriver: SqlDriver

    @Mock
    private lateinit var videoItemQuery: VideoItemQueries

    @Mock
    private lateinit var queryVideo: Query<VideoItem>

    private lateinit var database: KmmAppDatabase

    private lateinit var playlistRepository: PlaylistRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        database = KmmAppDatabase(sqlDriver)
        playlistRepository = PlaylistRepositoryImpl(videoPlaylistService, videoItemQuery)
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
        val expectedPlaylist = Playlist(
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
            `when`(videoPlaylistService.getVideos()).thenReturn(expectedPlaylist)
            `when`(videoItemQuery.getAll()).thenReturn(queryVideo)
            `when`(queryVideo.execute()).thenReturn(mock(SqlCursor::class.java))
            playlistRepository.getPlaylist()
        }
    }
}
