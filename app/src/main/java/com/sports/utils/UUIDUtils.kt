package com.sports.utils

import android.app.Application
import com.ut.device.UTDevice

lateinit var uuid: String

fun Application.initUUID() {
    uuid = UTDevice.getUtdid(this)
    uuid.logE("UUID：")
}