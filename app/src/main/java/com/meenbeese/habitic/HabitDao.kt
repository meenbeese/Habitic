package com.meenbeese.habitic

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert
    fun insertNewHabit(h: Habit): Long

    @Delete
    fun deleteHabit(h: Habit)

    @Update
    fun updateHabit(h: Habit)

    @Insert
    fun insertNewEvent(e: Event)

    @Delete
    fun deleteEvent(e: Event)

    @Update
    fun updateEvent(e: Event)

    @Query("SELECT * FROM habit")
    fun loadAllHabits(): Array<Habit>

    @Query("SELECT * FROM habit WHERE archived_2 == 0")
    fun loadNonArchivedHabits(): Array<Habit>

    @Query("SELECT * FROM habit WHERE id = :habitId")
    fun loadHabit(habitId: Long): Habit

    @Query("SELECT * FROM event WHERE habit_id = :habitId")
    fun loadAllEventsForHabit(habitId: Long): Array<Event>

    @Query("SELECT * FROM event WHERE habit_id = :habitId AND timestamp >= :timestamp")
    fun loadEventsForHabitSince(habitId: Long, timestamp: Long): Array<Event>

    @Query("SELECT * FROM event WHERE timestamp >= :timestamp")
    fun loadAllEventsSince(timestamp: Long): Array<Event>
}