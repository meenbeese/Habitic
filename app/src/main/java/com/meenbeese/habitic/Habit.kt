package com.meenbeese.habitic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    /**
     * Number of hours
     * e.g. For a X times / week event. This should be 7 * 24.
     */
    @ColumnInfo(name = "period")
    var period: Int,

    /**
     * Number of times / period
     * e.g. For a X times / week event. This should be X.
     */
    @ColumnInfo(name = "frequency")
    val frequency: Int,

    /**
     * If true, the habit is hidden from the UI, but still exists.
     */
    @ColumnInfo(name = "archived_2")
    val archived: Boolean
) {
    companion object {
        @JvmStatic
        fun csvHeader(): String {
            return "id,period,frequency,archived,title\n"
        }

        @JvmStatic
        fun fromCSV(csv: String): Habit {
            val parts = csv.split(",")
            if (parts.size < 5) {
                throw RuntimeException("invalid habit csv")
            }
            val id = parts[0].toLong()
            val period = parts[1].toInt()
            val frequency = parts[2].toInt()
            val archived = parts[3].toBoolean()
            val title = parts.subList(4, parts.size).joinToString(",")
            return Habit(id, title, period, frequency, archived)
        }
    }

    fun csv(): String {
        return "$id,$period,$frequency,$archived,$title"
    }
}
