package com.sports.utils

import android.content.Intent
import com.sports.LoginActivity
import com.sports.base.BaseActivity

/**
 * 跳转进入登录界面
 */
fun BaseActivity.loginActivity() {
    this.startActivity(Intent(this, LoginActivity::class.java))
}