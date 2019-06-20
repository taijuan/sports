package com.sports.model

import com.google.gson.annotations.SerializedName

/**
 * 直接解析data为非数组对象
 */
data class BaseRes<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("value") val data: T,
    @SerializedName("msg") val msg: String
)