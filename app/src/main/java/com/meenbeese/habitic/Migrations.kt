package com.meenbeese.habitic

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration

class Migrations {
    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE `habit` ADD COLUMN `archived_2` INTEGER" +
                            " NOT NULL DEFAULT `0`"
                )
            }
        }
    }
}