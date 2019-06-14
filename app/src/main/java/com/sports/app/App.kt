package com.sports.app

import android.app.Application
import androidx.annotation.Keep
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import com.sports.utils.initHttpDns
import com.sports.utils.initOkHttp

@Keep
class App : Application() {
    override fun onCreate() {
        initHttpDns()
        initOkHttp()
        QMUISwipeBackActivityManager.init(this)
        super.onCreate()
    }
}