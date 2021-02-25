package com.gamalinda.android.poc.shared.data.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*

actual class ApiClientFactory {
    actual fun createClient(): HttpClient {
        return HttpClient(CIO)
    }
}
