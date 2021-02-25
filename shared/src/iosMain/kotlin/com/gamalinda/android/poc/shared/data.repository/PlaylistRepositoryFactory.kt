package com.gamalinda.android.poc.shared.data.repository

import com.gamalinda.android.poc.shared.DriverFactory
import com.gamalinda.android.poc.shared.data.service.ApiClientFactory
import kmm.queries.shared.KmmAppDatabase

class PlaylistRepositoryFactory {
    fun create() : PlaylistRepository {
        val httpClient = ApiClientFactory().createClient()
        val db = KmmAppDatabase(DriverFactory().createDriver())
        return PlaylistRepositoryImpl(httpClient, db.videoItemQueries)
    }
}
