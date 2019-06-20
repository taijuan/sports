package com.sports.app

import android.app.Application
import androidx.annotation.Keep
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager
import com.sports.utils.initHttpDns
import com.sports.utils.initOkHttp
import com.sports.utils.logE
import com.sports.utils.setDebug

@Keep
class App : Application() {
    override fun onCreate() {
        initHttpDns()
        initOkHttp()
        QMUISwipeBackActivityManager.init(this)
        setDebug()
        super.onCreate()
    }

    override fun onTrimMemory(level: Int) {
        /**
         * 内存状态变化 TRIM_MEMORY_UI_HIDDEN：表示应用在后台
         */
        "onTrimMemory: $level".logE()
        super.onTrimMemory(level)
    }
}