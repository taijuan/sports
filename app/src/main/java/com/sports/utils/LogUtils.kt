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

fun Any.logE(name: Any = "") {
    if (debug) {
        Log.e(TAG, "$name$this")
    }
}

fun Any.logInfo(name: Any = "") {
    if (debug) {
        Log.i(TAG, "$name$this")
    }
}


fun Throwable.logT(name: Any = "") {
    if (debug) {
        Log.e(TAG, "$name", this)
    }
}