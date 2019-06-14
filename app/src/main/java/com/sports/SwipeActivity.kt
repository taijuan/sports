package com.sports

import android.os.Bundle
import android.util.Log
import com.sports.base.BaseActivity
import com.sports.utils.httpDns
import kotlinx.android.synthetic.main.activity_main.*

class SwipeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topBar.setTitle("swipe")
        topBar.addLeftBackImageButton().setOnClickListener {
            finish()
        }
        val ip = httpDns.getIpsByHostAsync("api.cdeclips.com")
        ip.forEach {
            Log.e("zuiweng", "ip -> $it")
        }
    }
}