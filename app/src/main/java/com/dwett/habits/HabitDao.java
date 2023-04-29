package com.dwett.habits;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface HabitDao {
    @Insert
    long insertNewHabit(Habit h);

    @Delete
    void deleteHabit(Habit h);

    @Update
    void updateHabit(Habit h);

    @Insert
    void insertNewEvent(Event e);

    @Delete
    void deleteEvent(Event e);

    @Update
    void updateEvent(Event e);

    @Query("SELECT * FROM habit")
    Habit[] loadAllHabits();

    @Query("SELECT * FROM habit WHERE archived_2 == 0")
    Habit[] loadNonArchivedHabits();

    @Query("SELECT * FROM habit WHERE id = :habitId")
    Habit loadHabit(long habitId);

    @Query("SELECT * FROM event WHERE habit_id = :habitId")
    Event[] loadAllEventsForHabit(long habitId);

    @Query("SELECT * FROM event WHERE habit_id = :habitId AND timestamp >= :timestamp")
    Event[] loadEventsForHabitSince(long habitId, long timestamp);

    @Query("SELECT * FROM event WHERE timestamp >= :timestamp")
    Event[] loadAllEventsSince(long timestamp);
}
