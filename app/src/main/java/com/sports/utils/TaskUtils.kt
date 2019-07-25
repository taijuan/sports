package com.sports.utils

import java.util.concurrent.Executors
import java.util.concurrent.Future

private val background = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors())


fun runOnBackground(body: () -> Unit): Future<*> {
    return background.submit {
        body.invoke()
    }
}