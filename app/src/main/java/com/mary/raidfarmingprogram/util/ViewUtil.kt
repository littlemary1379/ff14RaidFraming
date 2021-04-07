package com.mary.raidfarmingprogram.util

import android.app.Activity
import android.graphics.Color
import androidx.core.content.ContextCompat

object ViewUtil {
    fun setStatusBarColor(activity: Activity, colorId : Int) {
        activity.window.statusBarColor = ContextCompat.getColor(activity, colorId)
    }
}