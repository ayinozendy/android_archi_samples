package com.gamalinda.android.poc.shared

import kmm.queries.shared.KmmAppDatabase
import kmm.queries.shared.VideoItemQueries
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse

class VideoQueryTest : RobolectricTests() {

    lateinit var videoItemQueries: VideoItemQueries

    @BeforeTest
    fun setup() {
        val db = KmmAppDatabase(testDbConnection())
        videoItemQueries = db.videoItemQueries
    }

    @Test
    fun testing() {
        assertFalse {
            false
        }
    }
}
