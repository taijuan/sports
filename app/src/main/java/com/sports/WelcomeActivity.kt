package com.sports

import android.os.Bundle
import com.sports.base.BaseActivity
import com.sports.utils.loginActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        onPress.setOnClickListener { }
        window.decorView.postDelayed({
            loginActivity()
        }, 3000)
    }
}