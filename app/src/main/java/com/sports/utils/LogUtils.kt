package com.sports.utils

import android.util.Log
import androidx.annotation.Keep
import com.sports.BuildConfig

const val TAG = "LogUtils"
private var debug = BuildConfig.DEBUG
/**
 * 日志开关
 */
@Keep
fun setDebug() {
    debug = true
}

fun Any.logE() {
    if (debug) {
        Log.e(TAG, "$this")
    }
}

fun Any.logInfo() {
    if (debug) {
        Log.i(TAG, "$this")
    }
}


fun Throwable.logT() {
    if (debug) {
        Log.e(TAG, "$this", this)
    }
}