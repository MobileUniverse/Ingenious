package com.che.githubusers.data.local

import androidx.room.migration.Migration

internal object RoomVersionMigration {
    val migrations = arrayOf<Migration>()

    fun getMigration(startVersion: Int, endVersion: Int) =
        migrations.find { it.startVersion == startVersion && it.endVersion == endVersion }
}
