package com.sports.utils

import android.view.View

fun View.onClick(body: () -> Unit) {
    var m = 0L
    this.setOnClickListener {
        var c = System.currentTimeMillis()
        if (c - m >= 5000L) {
            body()
            m = c
        }
    }
}