package com.sports

import android.os.Bundle
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.sports.base.BaseActivity
import com.sports.utils.EncryptUtils
import com.sports.utils.logE
import com.sports.utils.onClick
import com.sports.utils.runOnBackground
import kotlinx.android.synthetic.main.include_top_bar.*
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ScheduledExecutorService

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

        val ordorInfo = "alipay_sdk=alipay-sdk-java-3.7.110.ALL&app_id=2016100200643641&biz_content=%7B%22body%22%3A%221%E4%B8%AA%E6%9C%88Vip%E6%98%9F%E8%BD%A8%E4%BD%93%E8%82%B2%2C%E4%B8%80%E5%A4%A9%E5%8F%AA%E8%A6%812.2%E5%85%83%22%2C%22out_trade_no%22%3A%22VIP201907250456305990000073562%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%221%E4%B8%AA%E6%9C%88Vip%E6%98%9F%E8%BD%A8%E4%BD%93%E8%82%B2%22%2C%22timeout_express%22%3A%2220m%22%2C%22total_amount%22%3A%2268.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fuat.fleica.com%2Fsport-api%2Fpay%2FalipayResultNotify&sign=B5VBFyywRoJaXZ08EsHwrt5wrOtNY4X9OlRoYBQavNDycuHMlK%2BRQ6aH9BrauOYvaQNM7CJY9KxGLwB2BRfXtjgmMava8q9c%2B3K%2BrUU0kMocLxjuxSAqx8oFNvwM0%2F9BBHYa7EbCxFY6kmmqpNWiQ4WohcKID8LQOerGhKGG3QsJMheV2ONNP4mhZzZE%2BqhgnBPcdRp%2FEtsmj6xR5cy%2BQ83Ci3vLxSk4BF%2BHe9L31NgHpGlfPAHtBSf8RA3eyqvNvw4PvcXV8eY4MpeeWcZt8E0mxtqu7yHb230AR2kLN1air4YdygkvqiEC6haKYgbzYkD%2FakxbNEUvCFZ0fb3EjA%3D%3D&sign_type=RSA2&timestamp=2019-07-25+12%3A56%3A30&version=1.0"
        runOnBackground {
            //沙箱测试配置
            EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
            val result = PayTask(this).payV2(ordorInfo,true)
            result.logE("ordorResult: ")
        }
    }
}

