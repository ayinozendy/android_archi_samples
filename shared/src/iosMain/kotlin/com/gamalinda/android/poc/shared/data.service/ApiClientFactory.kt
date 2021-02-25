package com.gamalinda.android.poc.shared.data.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

actual class ApiClientFactory {
    actual fun createClient(): HttpClient {
        return HttpClient(Ios) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                })
                accept(ContentType.Text.Plain)
            }
        }
    }
}
