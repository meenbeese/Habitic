package com.dwett.habits;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.migration.Migration;
import androidx.annotation.NonNull;

public class Migrations {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `habit` ADD COLUMN `archived_2` INTEGER" +
                    " NOT NULL DEFAULT `0`");
        }
    };
}
