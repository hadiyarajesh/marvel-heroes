package com.hadiyarajesh.marvel_heroes.utility

import android.util.Log

fun debugLog(msg: String, t: Throwable? = null) {
    Log.d(Constants.LOG_TAG, msg, t)
}
