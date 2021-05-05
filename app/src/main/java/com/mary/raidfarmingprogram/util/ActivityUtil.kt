package com.mary.raidfarmingprogram.util

import android.app.Activity
import android.content.Intent

object ActivityUtil {

    fun startActivityWithFinish(activity: Activity, cls : Class<*>){
        var intent = Intent(activity, cls)
        activity.startActivity(intent)
        activity.finish()
    }

    fun startActivityWithoutFinish(activity: Activity, cls : Class<*>){
        var intent = Intent(activity, cls)
        activity.startActivity(intent)
    }
}