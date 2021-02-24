package com.gamalinda.android.poc.shared

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import kmm.queries.shared.KmmAppDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = KmmAppDatabase.Schema,
            name = "kmm_app_database"
        )
    }
}
