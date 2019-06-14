package com.sports.utils

import android.util.Log

const val TAG = "LogUtils"
var debug = true
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