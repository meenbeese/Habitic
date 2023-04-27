package com.dwett.habits

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class DisplayExportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.export)

        val intent: Intent = intent
        val habits: String? = intent.getStringExtra("habits")
        val events: String? = intent.getStringExtra("events")

        val textView: TextView = findViewById(R.id.export_data)
        textView.text = "Habits\n${Habit.csvHeader()}$habits\n\nEvents\n${Event.csvHeader()}$events"
    }
}