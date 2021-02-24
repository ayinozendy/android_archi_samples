package com.gamalinda.android.poc.shared

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver() : SqlDriver
}
