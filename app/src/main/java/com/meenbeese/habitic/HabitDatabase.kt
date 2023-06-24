package com.meenbeese.habitic

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Habit::class, Event::class], version = 2)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}