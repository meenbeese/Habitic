package com.dwett.habits

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != null) {
            if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED, ignoreCase = true)) {
                // Set the alarm here.
                NotificationScheduler.scheduleAlarm(context, AlarmReceiver::class.java)
                return
            }
        }

        // Display the notification
        NotificationScheduler.showReminder(context, MainActivity::class.java)
    }
}