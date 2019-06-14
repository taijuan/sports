package com.sports.base

import com.qmuiteam.qmui.arch.QMUIActivity
import com.qmuiteam.qmui.util.QMUIDisplayHelper


abstract class BaseActivity : QMUIActivity() {

    override fun backViewInitOffset(): Int {
        return QMUIDisplayHelper.getScreenWidth(this) / 2
    }

    public override fun onResume() {
        super.onResume()
    }
}
