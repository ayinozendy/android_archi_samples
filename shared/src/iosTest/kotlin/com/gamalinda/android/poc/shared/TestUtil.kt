package com.gamalinda.android.poc.shared

import co.touchlab.sqliter.DatabaseConfiguration
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection
import kmm.queries.shared.KmmAppDatabase

internal actual fun testDbConnection(): SqlDriver {
    val schema = KmmAppDatabase.Schema
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "kmm_app_database.db",
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { schema.migrate(it, oldVersion, newVersion) }
            },
            inMemory = true,
            foreignKeyConstraints = true
        )
    )
}
