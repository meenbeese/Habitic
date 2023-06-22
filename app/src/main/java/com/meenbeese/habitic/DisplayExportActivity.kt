package com.meenbeese.habitic

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class DisplayExportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.export)

        val intent: Intent = intent
        val habits: String? = intent.getStringExtra("habits")
        val events: String? = intent.getStringExtra("events")

        val textView: TextView = findViewById(R.id.export_data)
        textView.text = "Habits\n${Habit.csvHeader()}$habits\n\nEvents\n${Event.csvHeader()}$events"

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { fabView ->
            val valueToCopy = textView.text

            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", valueToCopy)
            clipboard.setPrimaryClip(clip)

            Snackbar.make(fabView, "Text copied to clipboard", Snackbar.LENGTH_SHORT).show()
        }
    }
}