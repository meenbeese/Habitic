package com.meenbeese.habitic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

import java.time.Instant
import java.time.ZoneId

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Habit::class,
            parentColumns = ["id"],
            childColumns = ["habit_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class Event(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name = "habit_id") var habitId: Long = 0L,
    @ColumnInfo(name = "timestamp") var timestamp: Long = 0L
) {
    // Returns true if the time was adjusted, false otherwise
    fun maybeAdjustTimestampToPreviousDay(): Boolean {
        // If the time is between midnight and 3am, we want to record it
        // for the previous day at 11:59 instead.
        val dt = Instant.ofEpochMilli(this.timestamp)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
        if (dt.hour < 3) {
            val adjustedDateTime = dt.minusHours((dt.hour + 1).toLong())
                .plusMinutes((59 - dt.minute).toLong())
            this.timestamp = adjustedDateTime.atZone(ZoneId.systemDefault()).toEpochSecond()
            return true
        }
        return false
    }

    fun csvHeader(): String {
        return "id,habitId,timestamp\n"
    }

    fun csv(): String {
        return "$id,$habitId,$timestamp"
    }

    companion object {
        fun fromCSV(csv: String): Event {
            val parts = csv.split(",")
            if (parts.size != 3) {
                throw RuntimeException("invalid event csv")
            }
            val e = Event()
            e.id = parts[0].toLong()
            e.habitId = parts[1].toLong()
            e.timestamp = parts[2].toLong()
            return e
        }
    }
}