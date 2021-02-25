package com.gamalinda.android.poc.archisample.data.repository.impl

import com.gamalinda.android.poc.archisample.data.repository.PlaylistRepository
import com.gamalinda.android.poc.archisample.data.service.VideoPlaylistService
import com.gamalinda.android.poc.archisample.model.Playlist
import com.gamalinda.android.poc.archisample.model.Video
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.http.*
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

    private lateinit var ktorHttpClient: HttpClient

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

        ktorHttpClient = HttpClient(MockEngine) {
            engine {
                val jsonOutput = "{\n" +
                        "\"play_list\": [{\n" +
                        "\"description\": \"Big Buck Bunny tells the story of a giant rabbit with a heart bigger than himself. When one sunny day three rodents rudely harass him, something snaps... and the rabbit ain't no bunny anymore! In the typical cartoon tradition he prepares the nasty rodents a comical revenge.\\n\\nLicensed under the Creative Commons Attribution license\",\n" +
                        "\"video_url\": \"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4\",\n" +
                        "\"author\": \"By Blender Foundation\",\n" +
                        "\"thumbnail_url\": \"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg\",\n" +
                        "\"title\": \"Big Buck Bunny\"\n" +
                        "}]\n" +
                        "}"
                addHandler {
                    val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Text.Plain.toString()))
                    respond(jsonOutput, headers = responseHeaders)
                }
            }
            install(JsonFeature) {
                serializer = GsonSerializer {
                    accept(ContentType.Text.Plain)
                }
            }
        }
        database = KmmAppDatabase(sqlDriver)
        playlistRepository = PlaylistRepositoryImpl(ktorHttpClient, videoItemQuery)
    }

    @Test
    fun testFetchPlaylist() {
        runBlocking {
            playlistRepository.fetchPlaylist()
        }
    }

    @Test
    fun testGetPlaylist() {
        runBlocking {
            `when`(videoItemQuery.getAll()).thenReturn(queryVideo)
            `when`(queryVideo.execute()).thenReturn(mock(SqlCursor::class.java))
            playlistRepository.getPlaylist()
        }
    }
}
