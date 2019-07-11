package com.sports

import android.os.Bundle
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.sports.base.BaseActivity
import com.sports.utils.EncryptUtils
import com.sports.utils.logE
import com.sports.utils.onClick
import kotlinx.android.synthetic.main.include_top_bar.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        QMUIStatusBarHelper.setStatusBarLightMode(this)
//        topBar.setBackgroundDividerEnabled(false)
        topBar.addLeftBackImageButton().onClick {
            finish()
        }
        topBar.post {
            topBar.height.logE("height：")
        }
        topBar.addRightTextButton(R.string.reg, -1).onClick {

        }
        EncryptUtils.encryptMD5ToString("ChinaDaily", null).logE("MD5：")
    }
}