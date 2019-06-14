package com.sports.base

import com.qmuiteam.qmui.arch.QMUIFragment
import com.qmuiteam.qmui.util.QMUIDisplayHelper

abstract class BaseFragment : QMUIFragment() {

    override fun backViewInitOffset(): Int {
        return QMUIDisplayHelper.getScreenWidth(context) / 2
    }
}
