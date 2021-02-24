package com.gamalinda.android.poc.shared

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import kmm.queries.shared.KmmAppDatabase

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = KmmAppDatabase.Schema,
            context = context,
            name = "kmm_app_database"
        )
    }
}
