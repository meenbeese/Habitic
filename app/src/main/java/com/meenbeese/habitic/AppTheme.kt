package com.meenbeese.habitic

import android.app.Application
import com.google.android.material.color.DynamicColors

class AppTheme: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}