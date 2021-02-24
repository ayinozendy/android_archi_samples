package com.gamalinda.android.poc.shared

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import kmm.queries.shared.KmmAppDatabase

internal actual fun testDbConnection(): SqlDriver {
    val app = ApplicationProvider.getApplicationContext<Application>()
    return AndroidSqliteDriver(KmmAppDatabase.Schema, app, "kmm_app_database.db")
}
