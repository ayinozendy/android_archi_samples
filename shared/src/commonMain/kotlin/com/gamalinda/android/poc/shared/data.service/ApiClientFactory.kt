package com.gamalinda.android.poc.shared.data.service

import io.ktor.client.*

expect class ApiClientFactory {
    fun createClient() : HttpClient
}
